<template>
  <div>
    <div style="margin: 10px 10px">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button>其他</el-button>
    </div>

    <div style="margin-left: 10px">
      <el-input v-model="key" placeholder="Please input" style="width: 10%"/>
      <el-button style="margin-left: 10px" type="primary" @click="listByConditional">搜索</el-button>
    </div>

    <el-table :data="tableData" stripe style="width: 90%">
      <el-table-column prop="id" label="id" sortable/>
      <el-table-column prop="furnName" label="家居名"/>
      <el-table-column prop="maker" label="制造商"/>
      <el-table-column prop="price" label="价格"/>
      <el-table-column prop="sales" label="销量"/>
      <el-table-column prop="stock" label="库存"/>
      <el-table-column prop="isShow" label="上架">
        <template #default="scope">
          <el-switch
              v-model="scope.row.isShow"
              class="ml-2" style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
              @change="changeShow(scope.row)"
              :active-value="1"
              :inactive-value="0"
          />
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="120px">
        <template #default="scope">
          <el-popconfirm title="你确定要删除这个家居吗" @confirm="handleDel(scope.row.id)">
            <template #reference>
              <el-button type="text">删除</el-button>
            </template>
          </el-popconfirm>
          <el-button type="text" @click="handleEdit(scope.row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--表单对话框-->
    <el-dialog
        v-model="dialogVisible"
        title="表单修改"
        width="30%"
    >
      <el-form
          ref="ruleFormRef"
          :model="form"
          status-icon
          :rules="rules"
          label-width="120px"
          class="demo-ruleForm"
      >
        <el-form-item label="家具名" prop="furnName">
          <el-input v-model="form.furnName" type="text" autocomplete="off"/>
          {{serverValidErrors.furnName}}
        </el-form-item>
        <el-form-item label="制造商" prop="maker">
          <el-input
              v-model="form.maker"
              type="text"
              autocomplete="off"
          />
          {{serverValidErrors.maker}}
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price"/>
          <!--展示后端错误信息-->
          {{serverValidErrors.price}}
        </el-form-item>
        <el-form-item label="销量" prop="sales">
          <el-input v-model="form.sales"/>
          {{serverValidErrors.sales}}
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input v-model="form.stock"/>
          {{serverValidErrors.stock}}
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          提交
        </el-button>
      </span>
      </template>
    </el-dialog>
    <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[2, 5, 10, 20]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
// @ is an alias to /src
import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {},
  data() {
    return {
      serverValidErrors: {},
      tableData: [],
      key: "",
      dialogVisible: false,
      rules: {
        furnName: [
          {required: true, message: "请输入家居名", trigger: 'blur'}
        ],
        maker: [
          {required: true, message: "请输入制造商", trigger: 'blur'}
        ],
        price: [
          {required: true, message: "请输入价格", trigger: 'blur'},
          {pattern: /^(([1-9]\d+)|0)(\.\d+)?$/,message: "请输入数字",trigger: 'blur'}
        ],
        sales:[
          {required: true, message: "请输入销量", trigger: 'blur'},
          {pattern: /^(([1-9]\d+)|0)$/,message: "请输入正整数",trigger: 'blur'}
        ],
        stock:[
          {required: true, message: "请输入库存", trigger: 'blur'},
          {pattern: /^(([1-9]\d+)|0)$/,message: "请输入正整数",trigger: 'blur'}
        ]
      },
      form: {},
      currentPage: 1,
      pageSize: 5,
      total: 0,
    }
  },
  methods: {
    // 获取所有数据
    list() {
      console.log("list 怎么被调用了")
      request.get("/api/list").then(data => {
        this.tableData = data['datapools'].furnList
      })
    },
    // 响应添加按钮
    add() {
      this.dialogVisible = true;
      // 数据置空
      this.form = {}
      console.log("refs=>",this.$refs)
      this.$refs['ruleFormRef'].resetFields()
    },
    // 编辑按钮
    handleEdit(row) {
      this.dialogVisible = true
      // 这里是直接在前端获取数据,不安全
      // let json = JSON.parse(JSON.stringify(row));
      // this.form = json
      // 直接从后端获取数据
      request.get(`/api/find/${row.id}`).then(data => {
        if (data.code === 200) {
          console.log("data=> ", data)
          this.form = data['datapools']
        }
      })
    }
    ,
    // 正式添加数据
    save() {

      if (this.form.id) { // 修改数据
        request.put("/api/update", this.form).then(data => {
          console.log("修改成功")
          console.log('data', data)
        })
        this.list()
        this.dialogVisible = false
      } else { // 如果form中没有id,说明是添加数据
        this.$refs['ruleFormRef'].validate((validate)=>{
          if (validate){ // 通过校验
            console.log("发送数据")
            request.post("/api/save", this.form).then(res => {
              // 如果成功返回
              console.log("res=> ", res)
              if (res.code === "200") {
                console.log("进入判断体了")
                console.log(res.msg)
              }
              this.list()
              this.dialogVisible = false
            })
          }else {
            this.$message({
              type: 'error',
              message: '你验证校验还没通过'
            })
            return false
          }
        })
      }
    },
    handleDel(id) {
      let delId = id
      console.log("删除数据")
      request.delete(`/api/del`, {
        params: {
          id: delId
        }
      }).then(res => {
        if (res.code === "200") {
          console.log(res.msg)
          this.$message({
            message: '删除成功',
            type: 'success',
          })
        } else {
          this.$message({
            message: '删除失败',
            type: 'error',
          })
        }
        this.list()
      })
    },
    // 修改显示按钮
    changeShow(row) {
      // 注意,这边点击按钮后isShow已经发生改变了,
      // 所以不需要在这里进行修改isShow的值
      console.log("isShow=", row.isShow)
      let json = {id: row.id, isShow: row.isShow}
      console.log("isShow=>", json.isShow)
      console.log("json=>", json)
      request.put("/api/update", json).then(res => {
        if (res.code === 200) {
          console.log(res.msg)
        }
        this.list()
      })
    },
    handleCurrentChange(pageNum) {
      console.log("当前页码被改变")
      this.currentPage = pageNum
      this.listByConditional()
    },
    handleSizeChange(pageSize) {
      console.log("页码大小被改变")
      this.pageSize = pageSize
      this.listByConditional()

    },
    // 带条件的查询语句
    listByConditional() {

      request.get("/api/query", {
        params: {
          pageSize: this.pageSize,
          pageNum: this.currentPage,
          key: this.key
        }
      }).then(res => {
        if (res.code === "200") {
          console.log(res.msg)
          this.tableData = res['datapools']['pageInfo'].list
          this.total = res['datapools']['pageInfo'].total
        }
        console.log(res)
      })
    }
  },
  created() {
    // this.list()
    this.listByConditional()
  }

}
</script>
