<template>
  <div style="padding: 20px; box-sizing: border-box">
    <el-row :gutter="10" type="flex" justify="end" style="margin-bottom: 20px;">
      <el-col :span="4">
        <el-input v-model="searchVal" placeholder="请输入用户名" />
      </el-col>
      <el-col :span="2">
        <el-button type="primary" @click="search">查询</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" @click="add">新增</el-button>
      </el-col>
    </el-row>

    <table-c
      :table-data="tableData"
      :table-columns="tableColumns"
    >
      <template slot="operation" slot-scope="scope">
        <el-button v-if="scope.scope.row.roleId !== 0" type="text" size="small" @click.native.prevent.stop="edit(scope.scope.row)">
          编辑
        </el-button>
        <el-button v-if="scope.scope.row.roleId !== 0" type="text" size="small" @click.native.prevent.stop="del(scope.scope.row)">
          删除
        </el-button>
      </template>
    </table-c>

    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" label-width="80px">
        <el-form-item label="人员名称">
          <el-input v-model="form.username" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="form.roleId" placeholder="请选择角色">
            <!-- <el-option label="管理员" :value="0" /> -->
            <el-option label="教师" :value="1" />
            <el-option label="学生" :value="2" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onsubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'KnowledgePointsContent',
  data() {
    return {
      tableData: [],
      tableColumns: [
        { prop: 'username', label: '人员名称' },
        { prop: 'roleName', label: '角色' },
        { prop: '', label: '操作', fixed: 'right', operation: 1 }
      ],

      dialogFormVisible: false,
      searchVal: '',
      title: '',
      form: {}
    }
  },

  created() {
    this.search()
  },

  methods: {
    // 查询人员
    search() {
      this.$http.queryUser({
        username: this.searchVal
      }).then(res => {
        if (res.code === 200) {
          this.tableData = res.data.map(item => {
            return {
              ...item,
              roleName: item.roleName === 'ROLE_TEACHER' ? '教师' : item.roleName === 'ROLE_ADMIN' ? '管理员' : '学生'
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

    // 新增人员
    add() {
      this.title = '新增'
      this.form = {}
      this.dialogFormVisible = true
    },

    // 编辑人员
    edit(row) {
      // row 当前行数据
      this.title = '编辑'
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },

    // 删除人员
    del(row) {
      // row 当前行数据
      this.$http.delUser(`userId=${row.userId}`).then(res => {
        if (res.code === 200) {
          this.$message.success('删除成功')
          this.search()
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    onsubmit() {
      const api = this.title === '新增' ? this.$http.onsubmit : this.$http.updateUser
      api(this.form).then(res => {
        if (res.code === 200) {
          this.$message.success(res.msg)
          this.dialogFormVisible = false
          this.search()
        } else {
          this.$message.error(res.msg)
        }
      }).catch(err => {
        console.log(err, 'err')
      })
    }
  }
}
</script>

<style scoped>

</style>
