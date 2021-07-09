<template>
  <div class="dashboard-container">
    <el-row>
      <el-col :span="8" style="width: 450px;">
        <el-tree
          ref="tree"
          :data="treeData"
          show-checkbox
          node-key="id"
          default-expand-all
          draggable
          :expand-on-click-node="false"
          :props="defaultProps"
          @node-click="nodeClick"
        >
          <span slot-scope="{ node, data }" class="custom-tree-node" style="width: 100%; font-size: 14px; line-height: 30px;">
            <span style="float: left">{{ node.label }}</span>
            <span style="float: right">
              <el-button
                v-if="data.branchType == 2"
                type="text"
                size="mini"
                @click="() => addTree(data)"
              >
                添加知识点树
              </el-button>
              <el-button
                v-else
                type="text"
                size="mini"
                @click="() => add(data)"
              >
                新增知识点内容
              </el-button>
              <el-button
                type="text"
                size="mini"
                @click="() => edit(data)"
              >
                编辑
              </el-button>
              <el-popconfirm
                icon="el-icon-info"
                icon-color="red"
                title="确定删除吗？"
                @onConfirm="remove"
              >
                <el-button
                  slot="reference"
                  type="text"
                  size="mini"
                  @click="() => del(data)"
                >
                  删除
                </el-button>
              </el-popconfirm>

            </span>
          </span>
        </el-tree>
      </el-col>

      <el-col :span="16" style="width: calc(100% - 450px);">

        <el-table
          :data="tableData"
          style="width: 100%"
        >

          <el-table-column
            v-for="(item, index) in tableColumns"
            :key="index"
            :prop="item.prop"
            :label="item.label"
            :width="item.width || ''"
            :fixed="item.fixed"
            :align="item.align || 'center'"
          >
            <template slot-scope="scope">
              <el-button v-if="item.operation" type="text" @click="tableBtnClick(scope)">{{ item.content }}</el-button>
              <span v-else>{{ scope.row[item.prop] }}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <!-- add -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" label-width="80px">
        <el-form-item label="文字类型">
          <!-- 同步讲义、图书文字和授课讲义 -->
          <el-select v-model="form.textType" placeholder="请选择文字类型">
            <el-option label="同步讲义" value="1" />
            <el-option label="图书文字" value="2" />
            <el-option label="授课讲义" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="文字">
          <el-input v-model="form.text" autocomplete="off" />
        </el-form-item>
        <el-form-item label="文字链接">
          <el-input v-model="form.textUrl" autocomplete="off" />
        </el-form-item>
        <!-- <el-form-item label="题目">
          <el-input v-model="form.subject" autocomplete="off" />
        </el-form-item> -->
        <el-form-item label="图片">
          <el-upload
            v-model="form.picture"
            class="upload-demo"
            action="http://localhost:3397/knowledge/content/upload/pic"
            multiple
            :limit="3"
            name="picFile"
            :on-success="handlePicSuccess"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>

        <el-form-item label="视频">
          <el-upload
            v-model="form.video"
            class="upload-demo"
            action="http://localhost:3397/knowledge/content/upload/video"
            multiple
            :limit="3"
            name="videoFile"
            :on-success="handleVideoSuccess"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="音频">
          <el-upload
            v-model="form.mp3"
            class="upload-demo"
            action="http://localhost:3397/knowledge/content/upload/mp3"
            multiple
            :limit="3"
            name="mp3File"
            :on-success="handleMp3Success"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addKnowledgeContent">确 定</el-button>
      </div>
    </el-dialog>

    <!-- nodeName -->
    <el-dialog :title="addOrEdit" :visible.sync="addContentVisible" width="30%">
      <el-select v-model="addContentForm.branchType" :disabled="addOrEdit === '编辑'" placeholder="请选择添加类型" :clearable="false">
        <el-option label="知识点" value="1" />
        <el-option label="分支" value="2" />
      </el-select>
      <el-input v-model="addContentForm.branchName" style="margin-top: 10px;" clearable placeholder="请输入名称" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="addContentVisible = false">取 消</el-button>
        <el-button type="primary" @click="addTreeSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
const id = 1

export default {
  name: 'Dashboard',
  data() {
    return {
      form: {},
      dialogFormVisible: false,
      addContentForm: {
        branchName: '',
        branchType: '1',
        parentId: ''
      },
      treeData: [],
      defaultProps: {
        label: 'branchName',
        children: 'children',
        value: 'id'
      },
      addOrEdit: '',
      addContentVisible: false,
      addContentData: [],
      treeItem: {},
      delTreeId: '',
      tableData: [],
      title: '',
      tableColumns: [
        { prop: 'subject', label: '题目' },
        { prop: 'textType', label: '文字类型' },
        { prop: 'text', label: '文字' },
        { prop: 'textUrl', label: '文字链接' },
        { prop: 'picture', label: '图片', operation: 1, content: '下载' },
        { prop: 'video', label: '视频', operation: 1, content: '下载' },
        { prop: 'mp3', label: '音频', operation: 1, content: '下载' }
      ]
    }
  },
  watch: {
    dialogFormVisible(n) {
      if (!n) {
        this.form = {}
      }
    }
  },

  created() {
    this.queryKnowledgeContent()
    this.queryKnowledgeTree()
  },

  methods: {

    // 查询知识点树
    queryKnowledgeTree() {
      this.$http.queryKnowledgeTree({
        // username: this.searchVal
      }).then(res => {
        if (res.code === 200) {
          this.treeData = res.data
          console.log(res.data)
        } else {
          this.$message.error(res.msg)
        }
      }).catch(err => {
        console.log(err, 'err')
        this.$message.error('系统开小差, 请联系管理员!')
      })
    },

    add(data) {
      this.title = '新增'
      this.form.treeId = data.id
      this.form.subject = data.branchName
      this.dialogFormVisible = true
    },

    // 新增知识点内容
    addKnowledgeContent() {
      this.$http.addKnowledgeContent(this.form).then(res => {
        if (res.code === 200) {
          this.$message.success(res.msg)
          this.dialogFormVisible = false
          this.queryKnowledgeContent()
        } else {
          this.$message.error(res.msg)
        }
      }).catch(err => {
        console.log(err, 'err')
      })
    },

    // 查询知识点内容
    queryKnowledgeContent() {
      this.$http.queryKnowledgeContent({
        // username: this.searchVal
      }).then(res => {
        if (res.code === 200) {
          this.tableData = res.data.map(item => {
            return {
              ...item,
              textType: item.textType === 1 ? '同步讲义' : item.textType === 2 ? '图书文字' : '授课讲义'
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

    // 上传图片
    handlePicSuccess(res, file) {
      // this.imageUrl = URL.createObjectURL(file.raw);
      // 重点  得到上传图片的名字
      this.form.picture = res.data
      console.log(this.form)
    },

    // 上传视频
    handleVideoSuccess(res, file) {
      this.form.video = res.data
      console.log(this.form)
    },

    // 上传音频
    handleMp3Success(res, file) {
      this.form.mp3 = res.data
      console.log(this.form)
    },

    tableBtnClick(v) {
      console.log(v, 'vv')
      if (v.column.label === '操作') {
        this.editContent(v.row)
      } else {
        this.downloadFile(v.column.label, v.row)
      }
    },

    downloadFile(label, row) {
      // TODO label 表头 可区分是什么类型的文件(直接下载的话 不区分也行), row 行数据 直接调下载接口就行

    },

    editContent(row) {
      // TODO row 行数据
      this.form = { ...row }
      this.dialogFormVisible = true
    },

    nodeClick(data) {
      this.treeItem = data
      if (!data.children) {
        // TODO 点击课程后的逻辑在这处理

      }
    },

    addTree(data) {
      console.log(data)
      this.addOrEdit = '新增'
      this.addContentForm = {
        branchType: '1',
        branchName: ''
      }
      // TODO data里面有id
      this.addContentVisible = true
      // this.nodeName = ''
      this.addContentData.parentId = data.id
    },

    edit(data) {
      console.log(data)
      this.addOrEdit = '编辑'
      // TODO data里面有id
      this.addContentVisible = true
      this.addContentForm = {
        ...data,
        branchType: data.branchType + '',
        branchName: data.branchName
      }
      this.addContentData = data
    },

    del(data) {
      console.log(data)
      this.delTreeId = data.id
    },

    remove() {
      console.log(this.delTreeId)
      this.$http.delKnowledgeTree(`id=${this.delTreeId}`).then(res => {
        if (res.code === 200) {
          this.$message.success('删除成功')
          this.queryKnowledgeTree()
          this.queryKnowledgeContent()
        } else {
          this.$message.error(res.msg)
        }
      })
      // const parent = node.parent
      // const children = parent.data.children || parent.data
      // const index = children.findIndex(d => d.id === data.id)
      // children.splice(index, 1)
    },

    addTreeSubmit() {
      // TODO this.addContentForm 里面是参数  有id就是编辑 没有id就是新增
      this.addContentForm.parentId = this.addContentData.parentId
      console.log(this.addContentForm)
      if (!this.addContentForm.branchName.trim()) {
        return this.$message.warning('请输入名称')
      }
      const api = this.addOrEdit === '新增' ? this.$http.addKnowledgeTree : this.$http.updateKnowledgeTree
      api(this.addContentForm).then(res => {
        if (res.code === 200) {
          this.$message.success(res.msg)
          this.dialogFormVisible = false
          this.queryKnowledgeTree()
        } else {
          this.$message.error(res.msg)
        }
      }).catch(err => {
        console.log(err, 'err')
      })
      this.addContentVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }

  .custom-tree-node {
    width: 100%;
  }
}
</style>
