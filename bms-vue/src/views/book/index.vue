<template>
  <div style="height: 100%;">
    <!-- search -->
    <div id="expressage" style="height: 100px;">
      <div class="headbox">
        <el-input
                size="mini"
                v-model="searchForm.bookName"
                placeholder="书籍名"
                class="searchbox"
        />
        <el-input
                size="mini"
                v-model="searchForm.author"
                placeholder="作者"
                class="searchbox"
        />
        <el-button
                size="mini"
                type="primary" style="height: 30px;margin-top: 14px;"
                @click="query"
        >
          <i class="el-icon-search"/>
          查询</el-button
        >
        <el-button style="height: 30px;margin-top: 14px;"
                   size="mini"
                   type="primary" plain
                   @click="reset"
        >
          <i class="el-icon-refresh"/>
          重置</el-button
        >
      </div>
      <div class="headbox">
        <el-button
                size="mini"
                type="primary" plain
                style="height: 30px;;margin-left: 15px"
                @click="toAdd"
        >
          <i class="el-icon-plus"/>
          新增
        </el-button
        >
      </div>
    </div>
    <!-- table -->
    <el-table
              ref="listPowerSupplyTab"
              :data="tableData"
              border
              height="75%"
              highlight-current-row
              :stripe="true"
              v-loading="listLoading"
              :default-sort = "{prop: 'data', order: 'descending'}">
      <el-table-column
              fixed
              label="ID"
              type="index"
              width="50"/>
      <el-table-column label="书籍名" sortable prop="bookName" width="">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <span size="medium">{{ scope.row.bookName}}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="作者" sortable prop="author" width="">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <span size="medium">{{ scope.row.author }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="类型" sortable prop="type"  >
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <span size="medium">{{ scope.row.type }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="发布人/机构" sortable prop="publisher" width="">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper" >
            <span size="medium">{{ scope.row.publisher }}</span>
          </div>
        </template>
      </el-table-column >
      <el-table-column label="发布时间" sortable prop="publicationDate" width="">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper" >
            <span size="medium">{{ scope.row.publicationDate }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="价格" sortable prop="price" width="">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper" >
            <span size="medium">{{ scope.row.price }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="备注" sortable prop="remark" width="">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper" >
            <span size="medium">{{ scope.row.remark }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作"  width="220">
        <template slot-scope="scope">
          <el-button
                  style="margin-left: 10px"
                  size="mini"
                  type="primary" plain
                  @click="toEdit(scope.row)"
          ><i class="el-icon-edit"/>
            编辑</el-button
          >
          <el-popconfirm
                  confirmButtonText="OK"
                  cancelButtonText="Cancel"
                  Theme="el-Theme-info"
                  ThemeColor="red"
                  title="您确定要删除么？"
                  @onConfirm="doDelete(scope.row.bid)"
          >
            <el-button style="margin-left: 10px"
                       slot="reference"
                       size="mini"
                       type="danger"
            >  <i class="el-icon-delete"/>
              删除</el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--add-->
    <el-dialog :visible.sync="isShow" width="600px"
               :close-on-click-modal="false"
               :destroy-on-close="true"
               center
               :show-close="false"
               :title="title"
    >
      <el-form ref="form" :model="form" label-width="100px" :rules="rules" center>
        <el-form-item label="书籍名:" required prop="bookName">
          <el-input size="mini" maxlength="50"  v-model="form.bookName"/>
        </el-form-item>
        <el-form-item label="作者：" required prop="author">
          <el-input size="mini" maxlength="80 "  v-model="form.author"/>
        </el-form-item>
        <el-form-item label="类型：" required prop="type">
          <el-select v-model="form.type"  size="mini" placeholder="请选择">
            <el-option
                    v-for="item in [{value: '玄幻',label: '玄幻'},{value: '计算机',label: '计算机'},{value: '科学',label: '科学'},{value: '其他',label: '其他'}]"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发布人/机构:" required prop="publisher">
          <el-input size="mini" maxlength="80 "  v-model="form.publisher"/>
        </el-form-item>
        <el-form-item label="发布时间:" required prop="publicationDate">
          <el-date-picker
                  size="mini"
                  v-model="form.publicationDate"
                  type="date"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="价格:" required prop="price">
          <el-input size="mini" maxlength="80 "  v-model="form.price"/>
        </el-form-item>
        <el-form-item label="备注:">
          <el-input
                  type="textarea"
                  :rows="2"
                  placeholder="请输入内容"
                  v-model="form.remark">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmData"><i class="el-icon-check"></i>提交</el-button>
        <el-button @click="toAdd"><i class="el-icon-close"></i>取消</el-button>
      </span>
    </el-dialog>
    <!--pagination component-->
    <pagination v-if="usePagination" ref="pagination"/>
    <!--operation component-->
    <operation  ref="operation"/>
  </div>
</template>
<script>
  import {eventBus} from "@/scripts/utils/BUS";
  import urls from "@/scripts/api";
  import pagination from "@/component/pagination.vue";
  import operation from "@/component/operation.vue";

  export default {
    components: {
      pagination,
      operation
    },
    data() {
      return {
        //--------------need to edit
        list_url:urls.book_list,
        create_url:urls.book_create,
        update_url:urls.book_update,
        delete_url:urls.book_delete,
        eventBus:'Theme_eventBus',
        editTitle:"编辑书籍",
        addTitle:"新增书籍",
        idType:'bid',
        usePagination:true,
        rules: {
          bookName: [
            {required: true, message: "请输入书籍名字"},
          ],
          author: [
            {required: true, message: "请输入作者名字"},
          ],
          type: [
            {required: true, message: "请输入类型"}
          ],
          publisher: [
            {required: true, message: "请输入发布人或者机构"}
          ],
          publicationDate: [
            {required: true, message: "请选择发布时间"}
          ],
          price: [
            {required: true, message: "请输入价格"}
          ]
        },
        //------------- no edit-------
        title: null,
        searchForm: {},
        form: {
          lightColor:'#409EFF'
        },
        tableData: [],
        isEdit: false,
        isShow: false,
        listLoading: false,
      };
    },

    watch: {
      form:{
        handler(val,oldVal) {
          try{
            this.form.themeFlag = val.themeFlag.replace(/\D/g,'');
          }catch (e) {

          }
        },
        deep: true //true 深度监听
      }
    },

    mounted() {
      eventBus.$on(this.eventBus, (tableData) => {
        if (tableData) {
          this.listLoading = false;
          this.tableData = tableData;
        } else {
          this.isEdit = false;
          this.query()
        }
        this.isShow = false;
      });
      //Whether or not to use paging queries
      this.usePagination ? this.query() : this.noPageQuery();
    },

    beforeDestroy() {
      eventBus.$off(this.eventBus)
    },

    methods: {

      reset(){
        this.searchForm = {};
        this.usePagination ? this.query() : this.noPageQuery();
      },

      doDelete(id) {
        let aa = "删除成功"
        this.$axios.get(this.delete_url+'?bid='+id).then(res => {
          this.$alert(aa, '', {
            confirmButtonText: "确定",
            callback: () => {
              this.query()
            }
          });
        }).catch((e) => {
          this.$alert('删除失败', '', {
            confirmButtonText: "OK",
          });
          return false;
        });
      },

      noPageQuery() {
        this.$refs.operation.$noPageQuery(this.list_url, this.eventBus);
      },

      query() {
        this.$refs.pagination.$query(this.list_url, this.searchForm, this.eventBus);
      },

      toAdd() {
        this.isEdit=false,
         this.title = this.addTitle;
        this.form = {};
        this.$nextTick(() => {
          if (this.$refs.form) {
            this.$refs.form.clearValidate()
          }
        });
        this.isShowDialog();
      },

      isShowDialog() {
        this.isShow = this.isShow !== true;
      },

      toEdit(row){
        this.title = this.editTitle;
        this.isEdit = true;
        this.form = JSON.parse(JSON.stringify(row));
        this.isShowDialog();
      },

      //Add with the editor go this method
      confirmData() {
        let url;
        let data;
        let text = "新增成功！";
        if (this.isEdit) {
          text = "修改成功！";
          url = this.update_url;
          data = {
            params: {
              'bid': this.form.bid,
              'bookName': this.form.bookName,
              'author': this.form.author,
              'type': this.form.type,
              'publisher': this.form.publisher,
              'publicationDate': this.form.publicationDate,
              'price': this.form.price,
              'remark': this.form.remark
            }
          }
        } else {
          url = this.create_url;
            data = {
            params: {
              'bookName': this.form.bookName,
              'author': this.form.author,
              'type': this.form.type,
              'publisher': this.form.publisher,
              'publicationDate': this.form.publicationDate,
              'price': this.form.price,
              'remark': this.form.remark
            }
          }
        }



        this.$refs.form.validate((valid) => {
          if (valid) {
            this.$refs.operation.$ConfirmData(url, data, text, this.eventBus);
          } else {
            return false;
          }
        });
      }
    }
  };
</script>
<style lang="scss" scoped>
  #expressage {
    .infobox {
      margin: 12px 0;
    }

    .headbox {
      display: flex;
      flex-wrap: nowrap;
      width: 100%;
    }

    .searchbox {
      margin: 15px;
      width: 10.6%;
    }
  }
</style>
