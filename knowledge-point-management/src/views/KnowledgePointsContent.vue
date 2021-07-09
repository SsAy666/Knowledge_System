<template>
  <div style="padding: 20px; box-sizing: border-box">
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="form" label-width="80px">
        <el-form-item label="文字类型">
          <!-- 同步讲义、图书文字和授课讲义 -->
          <el-select v-model="form.textType" placeholder="请选择文字类型">
            <el-option label="同步讲义" :value="1" />
            <el-option label="图书文字" :value="2" />
            <el-option label="授课讲义" :value="3" />
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
        <el-button type="primary" @click="editKnowledgeContent">确 定</el-button>
      </div>
    </el-dialog>

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
      <el-table-column
        label="操作"
        fixed="right"
        :align="'center'"
      >
        <template slot-scope="scope">
          <el-button type="text" @click="editContent(scope.row)">编辑</el-button>
          <el-button type="text" @click="deleteContent(scope.row)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
export default {
  name: 'KnowledgePointsContent',
  data() {
    return {
      tableData: [],
      tableColumns: [
        { prop: 'subject', label: '题目' },
        { prop: 'textTypeName', label: '文字类型' },
        { prop: 'text', label: '文字' },
        { prop: 'textUrl', label: '文字链接' },
        { prop: 'picture', label: '图片', operation: 1, content: '下载' },
        { prop: 'video', label: '视频', operation: 1, content: '下载' },
        { prop: 'mp3', label: '音频', operation: 1, content: '下载' },
        // { prop: '', label: '操作', fixed: 'right', operation: 1 }
      ],
      title: '',
      dialogFormVisible: false,
      form: {}
    }
  },

  created() {
    this.queryKnowledgeContent()
  },

  methods: {
    editContent(row) {
      console.log(row)
      // row 当前行数据
      this.title = '编辑'
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
      
    },

    editKnowledgeContent() {
      this.$http.updateKnowledgeContent(this.form).then(res => {
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

    deleteContent(row) {
      this.$http.delKnowledgeContent(`id=${row.id}`).then(res => {
        if (res.code === 200) {
          this.$message.success('删除成功')
          this.queryKnowledgeContent()
        } else {
          this.$message.error(res.msg)
        }
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
              textTypeName: item.textType === 1 ? '同步讲义' : item.textType === 2 ? '图书文字' : '授课讲义'
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

  }
}
</script>

<style scoped>

</style>
