<template>

    <div>
      <el-form :inline="true"  class="demo-form-inline">

        <el-form-item label="活动区域">
          <el-select v-model="apiName" placeholder="请选择">
            <el-option v-for="api of apis" :label="api.name" :value="api.name" :key="api.name"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button icon="el-icon-search" @click="search" circle></el-button>
        </el-form-item>   
        <el-form-item>
          <el-button icon="el-icon-circle-plus" @click="dialog.show = true" circle></el-button>
        </el-form-item>  
      </el-form>

      <el-table
        :data="tableData"
        style="width: 100%"
        :max-height="600">

        <el-table-column 
          v-for="(c , i) in columns" 
          :key="i"
          :prop="c.prop"
          :label="c.label"
        >
        </el-table-column>

      </el-table>

      <!-- <DialogApi :dialog="dialog" :form="form" @update="addApi"></DialogApi>  -->

      <panel :dialog="dialog"  ></panel>
    </div>

    
</template>

<script>
import DialogApi from '../components/DialogApi';
import Panel from '../components/chip_detail/Panel';
let beseApis = [
  {
    name:'区列表',
    url:'/demo/test/test'
  }
];

function getAPis(){
  let store = localStorage.getItem('APIS');
  if(store == null){
    return beseApis;
  }else{
    let apis = null;
    try {
      apis = JSON.parse(store);
    } catch (error) {
      apis = beseApis;
    }    
    return apis;
  }
}

let apis = getAPis();

export default {
  name:'areaList',
  components:{DialogApi , Panel},
  data(){
    return {
      tableData:[],
      apis,
      apiName:apis[0].name,
      columns:[],
      apiUrl: apis[0].url,
      dialog:{title:'添加api' , show:false},
      form:{name:'',url:''}
    }
  },
  watch:{
    apiName(){
      let apiName = this.apiName;
      let [current] = this.apis.filter(function(item){
          let name = item.name;
          return name === apiName
      });
      this.columns = current.columns;
      this.apiUrl = current.url;
      this.search();
    }
  },
  methods:{
    addApi(){

    },
    search(){
      this.$axios.get(this.apiUrl)
      .then(res=>{
        let {data} = res;
        this.tableData = data.list;
        let [item] = data.list;
        this.columns = Object.keys(item).map((e)=>{
          return {prop:e , label:e};
        });
      });
    }
  },
  created(){
    this.search();
  }
}
</script>
