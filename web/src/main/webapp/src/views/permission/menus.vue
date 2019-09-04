<template>
  <div class="app-container">

    <el-form :inline="true">
      <el-form-item >
        <!-- 新增菜单按钮 -->
        <el-button type="primary" @click="handleAdd">
          {{ $t('permission.addMenu') }}
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 菜单列表 -->
    <el-table :data="menuList" style="width: 100%;margin-top:30px;" border>
      <!--ID-->
      <el-table-column align="center" label="ID" >
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>

      <!-- 路径 -->
      <el-table-column align="center" label="Path" >
        <template slot-scope="scope">
          {{ scope.row.path }}
        </template>
      </el-table-column>

      <!-- 父ID -->
      <el-table-column align="header-center" label="Parent ID">
        <template slot-scope="scope">
          {{ scope.row.pid | parentIdFilter}}
        </template>
      </el-table-column>

      <!-- 描述 -->
      <el-table-column align="header-center" label="Description">
        <template slot-scope="scope">
          {{ scope.row.description  }}
        </template>
      </el-table-column>

      <!--状态: 0 禁用 1 正常-->
      <el-table-column align="header-center" label="Status">
        <template slot-scope="scope">
          {{ scope.row.isDel | statusText}}
        </template>
      </el-table-column>

      <!--权限字符串-->
      <el-table-column align="header-center" label="permission">
        <template slot-scope="scope">
          {{ scope.row.perms }}
        </template>
      </el-table-column>

      <!--操作-->
      <el-table-column align="center" label="Operations">
        <template slot-scope="scope">
          <el-button type="primary" v-waves size="small" @click="handleEdit(scope)">
            {{ $t('permission.editMenu') }}
          </el-button>
          <el-button type="danger" v-waves size="small" @click="handleDelete(scope)">
            {{ $t('permission.delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currPage"
        :page-sizes="[10 , 20]"
        :page-size="pageLimit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount">
      </el-pagination>
    </div>

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit Menu':'New Menu'">
      <el-form :model="menu" label-width="80px" label-position="left">

        <!--路径-->
        <el-form-item label="Path">
          <el-input v-model="menu.path" placeholder="Path used to match" />
        </el-form-item>

        <!--权限字符串-->
        <el-form-item label="Permission">
          <el-input v-model="menu.perms" placeholder="Permission Codes" />
        </el-form-item>

        <!--排序号-->
        <el-form-item label="Sort">
          <el-input v-model="menu.sort" placeholder="Sort priority" />
        </el-form-item>

        <!--pid-->
        <el-form-item label="PID">
          <el-select v-model="menu.pid" placeholder="Parent ID">
            <el-option
              v-for="m in rootMenuList"
              :key="m.id"
              :label="m.path"
              :value="m.id">
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 描述-->
        <el-form-item label="Desc">
          <el-input
            v-model="menu.description"
            :autosize="{ minRows: 2, maxRows: 4}"
            type="textarea"
            placeholder="Menu Description"
          />
        </el-form-item>

      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import waves from '@/directive/waves/index.js' // 水波纹指令
  import { deepClone } from '@/utils'
  import { getDataPage , getRootMenuList, delMenu, updateMenu , addMenu} from "@/api/auth/menu";
  import { asyncRoutes } from '@/router'

  const defaultMenu = {
    path: '',
    pid: 0,
    sort: 0,
    perms: ''
  }

  const rootMenuMap = {}

  export default {
    name: "menus",
    directives: {
      waves
    },
    data(){
      return {
        menu: Object.assign({} ,defaultMenu),
        pageLimit: 10,
        currPage: 1,
        page: { list: []},
        dialogVisible: false,
        dialogType: 'new',
        rootMenuList: []
      }
    },
    filters: {
      statusText( status ){
        return {
          '0': '禁用',
          '1': '正常'
        }[status]
      },
      parentIdFilter: function ( pid ){
        let path = rootMenuMap[pid]
        if (path) {
          return path
        }else if(pid === 0){
          return '[root]'
        }
        return pid
      }
    },
    computed: {
      menuList(){
        return this.page.list
      }
    },
    watch:{
      currPage() {
        this.getDataPage()
      },
      pageLimit(){
        this.getDataPage()
      }
    },
    created(){
      this.getDataPage()
      this.getRootMenuList()
    },
    methods: {
      async getDataPage(){
        const res = await getDataPage({page: this.currPage , limit: this.pageLimit})
        this.page = res.page
      },
      async getRootMenuList(){
        const res = await getRootMenuList()
        Array.isArray(res.list) && res.list.forEach(root =>{
          rootMenuMap[root.id] = root.path
        })
        this.rootMenuList = res.list
      },
      handleAdd(){
        this.menu = Object.assign({}, defaultMenu)
        this.dialogType = 'new'
        this.dialogVisible = true
      },
      handleEdit( scope ){
        this.dialogType = 'edit'
        this.dialogVisible = true
        this.menu = deepClone(scope.row)
      },
      handleDelete({ $index, row }){
        this.$confirm('Confirm to remove the Menu?', 'Warning', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        })
          .then(async() => {
            await delMenu(row.id)
            this.getDataPage()
            this.$message({
              type: 'success',
              message: 'Delete succed!'
            })
          })
          .catch(err => { console.error(err) })
      },
      async confirm(){
        const isEdit = this.dialogType === 'edit'
        if (isEdit) {
          await updateMenu(this.menu)
        } else {
          await addMenu(this.menu)
        }

        if (this.currPage === 1 ){
          this.getDataPage()
        }

        const { description, path } = this.menu
        this.dialogVisible = false
        this.$notify({
          title: 'Success',
          dangerouslyUseHTMLString: true,
          message: `
            <div>Path: ${path}</div>
            <div>Description: ${description}</div>
          `,
          type: 'success'
        })
      },
      handleSizeChange(val) {
        this.pageLimit = val
      },
      handleCurrentChange(val) {
        this.currPage = val
      }
    }
  }
</script>

<style lang="scss" scoped>
  .app-container {
    .roles-table {
      margin-top: 30px;
    }
  }
</style>
