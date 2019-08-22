<template>

  <el-dialog
      :title="dialog.title"
      :visible.sync="dialog.show"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :modal-append-to-body="false"
      width="80%"
    >

      <el-collapse v-model="activeName" accordion>
        <el-collapse-item v-for="(chip , index) in chips" :key="chip.coinTypeId" :title="chip.coinCode" :name="index">
          <el-col v-for="chipItem of chip.dataList" :key="chipItem.key" :span="chipItem.span">
            
            <el-table
              :data="chipItem.list"
              :max-height="500"
              size="mini"
              >
              <el-table-column header-align="center" :label="chipItem.key">
                  <el-table-column
                    prop="chipGroup.groupCode"
                    label="筹码组"
                    header-align="center"
                    align="center"
                  ></el-table-column>

                  <el-table-column
                    prop="cost"
                    label="面值"
                    header-align="center"
                    align="center"
                  ></el-table-column>

                  <el-table-column
                    prop="itemCount"
                    label="数量"
                    header-align="center"
                    align="center"
                  ></el-table-column>

                  <el-table-column
                    prop="itemTotal"
                    label="总值"
                    header-align="center"
                    align="center"
                  ></el-table-column>
              </el-table-column>
            </el-table>  

          </el-col>
        </el-collapse-item>
        
      </el-collapse> 

  </el-dialog> 
</template>

<script>
import chips from './chips';
import {cToPanelDataList} from './chipTools';
export default {
  name:'panel',
  props: {
    dialog: Object
  },
  data() {
    return {
      activeName: '0',
      chips:cToPanelDataList(chips)
    };
  },
  created(){
    console.log(this.chips);
  }
}
</script>

<style>

</style>
