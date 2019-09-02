<template>
  <div class="app-container">

    <el-form :inline="true">
      <el-form-item >
        <!-- 新增用户按钮 -->
        <el-button type="primary" @click="handleAdd">
          <!--{{ $t('permission.addDepartment') }}-->
          新增用户
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 用户列表 -->
    <el-table :data="userList" style="width: 100%;margin-top:30px;" border>
      <!-- 名称 -->
      <el-table-column align="center" label="Name" width="220">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>

      <!-- 描述 -->
      <el-table-column align="header-center" label="Description">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <!--操作-->
      <el-table-column align="center" label="Operations">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope)">
            <!--{{ $t('permission.editDepartment') }}-->
            编辑用户
          </el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">
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

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit Department':'New Department'">
      <el-form :model="user" label-width="80px" label-position="left">

        <!--名称-->
        <el-form-item label="Name">
          <el-input v-model="user.username" placeholder="User Name" />
        </el-form-item>

        <!-- 描述-->
        <el-form-item label="Desc">
          <el-input
            v-model="user.description"
            :autosize="{ minRows: 2, maxRows: 4}"
            type="textarea"
            placeholder="User Description"
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
  import { deepClone } from '@/utils'
  import { getDataPage , delUser, updateUser , addUser} from "@/api/auth/user";

  const defaultUser = {
    username: '',
    password: '',
    freePwd: '',
    status: 1,
    departmentId: 1,
    roles: []
  }

  export default {
    name: "users",
    data(){
      return {
        user: Object.assign({} ,defaultUser),
        pageLimit: 10,
        currPage: 1,
        page: { list: []},
        dialogVisible: false,
        dialogType: 'new',
        queryText: ''
      }
    },
    computed: {
      userList(){
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
    },
    methods: {
      async getDataPage(){
        const res = await getDataPage({page: this.page.currPage , limit: this.pageLimit})
        this.page = res.page
      },
      handleAdd(){
        this.department = Object.assign({}, defaultUser)
        this.dialogType = 'new'
        this.dialogVisible = true
      },
      handleEdit( scope ){
        this.dialogType = 'edit'
        this.dialogVisible = true
        this.department = deepClone(scope.row)
      },
      handleDelete({ $index, row }){
        this.$confirm('Confirm to remove the User?', 'Warning', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        })
          .then(async() => {
            await delUser(row.userId)
            this.departmentList.splice($index, 1)
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
          await updateUser(this.user)

        } else {
          await addUser(this.user)
        }

        if (this.currPage === 1 ){
          this.getDataPage()
        }

        const { description, name } = this.user
        this.dialogVisible = false
        this.$notify({
          title: 'Success',
          dangerouslyUseHTMLString: true,
          message: `
            <div>User Nmae: ${name}</div>
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
