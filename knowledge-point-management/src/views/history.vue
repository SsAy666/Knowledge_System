<template>
  <div style="padding: 20px; box-sizing: border-box">

    <table-c
      :table-data="tableData"
      :table-columns="tableColumns"
    >
      <!-- <template slot="operation" slot-scope="scope">
        <el-button type="text" size="small" @click.native.prevent.stop="edit(scope.scope.row)">
          编辑
        </el-button>
        <el-button type="text" size="small" @click.native.prevent.stop="del(scope.scope.row)">
          删除
        </el-button>
      </template> -->
    </table-c>
  </div>
</template>

<script>
export default {
  name: 'KnowledgePointsContent',
  data() {
    return {
      tableData: [],
      tableColumns: [
        { prop: 'operator', label: '操作人' },
        { prop: 'operateStyle', label: '操作方式' },
        { prop: 'operateBeforeContent', label: '操作前内容' },
        { prop: 'operateAfterContent', label: '操作后内容' },
        { prop: 'operateTime', label: '操作时间' }
        // { prop: '', label: '操作', fixed: 'right', operation: 1 }
      ]
    }
  },

  created() {
    this.search()
  },

  methods: {

    // 查询历史记录
    search() {
      this.$http.queryHistoryRecords({
        username: this.searchVal
      }).then(res => {
        if (res.code === 200) {
          this.tableData = res.data.map(item => {
            return {
              ...item,
              operateTime: this.$utils.dateFormat('yyyy-MM-dd hh:mm:ss', item.operateTime),
              operateStyle: item.operateStyle === 1 ? '新增' : item.operateStyle === 2 ? '编辑' : '删除'
            }
          })
        } else {
          this.$message.error(res.msg)
        }
      }).catch(err => {
        console.log(err, 'err')
        this.$message.error('系统开小差, 请联系管理员!')
      })
    },

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
