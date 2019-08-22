<template>
  <div class="logFund">
    <el-dialog
      :title="dialog.title"
      :visible.sync="dialog.show"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :modal-append-to-body="false"
    >
      <div class="form">
        <el-form
          ref="form"
          :model="form"
          label-width="120px"
          style="margin:10px;width:auto;"
        >

          <el-form-item prop="income" label="name:">
            <el-input type="income" v-model="form.income"></el-input>
          </el-form-item>

          <el-form-item prop="expend" label="url:">
            <el-input type="expend" v-model="form.expend"></el-input>
          </el-form-item>

          <el-form-item class="text_right">
            <el-button @click="dialog.show = false">取 消</el-button>
            <el-button type="primary" @click="onSubmit('form')">提 交</el-button>
          </el-form-item>

        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "logapi",
  props: {
    dialog: Object,
    form: Object
  },
  methods: {
    onSubmit(form) {
      this.$emit("update");
    }
  }
};
</script>

