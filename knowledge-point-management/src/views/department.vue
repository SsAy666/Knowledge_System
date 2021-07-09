<template>
  <div style="padding: 20px; box-sizing: border-box">
    <!--<el-row type="flex" justify="end" style="margin-bottom: 20px;">
      <el-col :span="2">
        <el-button type="primary" @click="add">新增</el-button>
      </el-col>
    </el-row>-->

    <table-c
      :table-data="tableData"
      :table-columns="tableColumns"
    >
      <template slot="operation" slot-scope="scope">
        <el-button type="text" size="small" @click.native.prevent.stop="edit(scope.scope.row)">
          编辑
        </el-button>
        <el-button type="text" size="small" @click.native.prevent.stop="del(scope.scope.row)">
          删除
        </el-button>
      </template>
    </table-c>
  </div>
</template>

<script>
export default {
  name: 'KnowledgePointsContent',
  data() {
    return {
      tableData: [{ name: '语文' }],
      tableColumns: [
        { prop: 'name', label: '课程名称' },
        { prop: 'name', label: '文字' },
        { prop: 'name', label: '视频' },
        { prop: 'name', label: '音频' },
        { prop: 'name', label: '图片' },
        { prop: '', label: '操作', fixed: 'right', operation: 1 }
      ]
    }
  },

  methods: {
    edit(row) {
      // row 当前行数据
    },
    del(row) {
      // row 当前行数据
    }
  }
}
</script>

<style scoped>

</style>
