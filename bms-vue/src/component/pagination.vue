<template>
    <div>
    <!-- pages -->
        <div style="display:flex;justify-content: center;align-items:center;">
        <el-pagination style="margin-top: 15px"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pagination.currentPage"
                :page-sizes="[5,10, 20, 50,100]"
                :page-size="pagination.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="pagination.total"
        ></el-pagination>
    </div>
    </div>
</template>

<script>
    import Moment from "moment";
    import Vue from "vue";
    import { eventBus } from "scripts/utils/BUS";
    import qs from 'qs';
    export default {
        components: {},
        data () {
            return {
                pagination: {
                    pageSize: 10,
                    total: 0,
                    currentPage: 1
                },
                bus:null,
                url:null,
                searchForm:null
            };
        },
        computed: {},

        mounted(){},

        methods: {

            /*
            All paging queries use this method
            * url:
            * searchForm:
            * bus:
            * */
            $query(url,searchForm,bus,nest) {
                this.url = url;
                this.bus = bus;
                this.searchForm = JSON.parse(JSON.stringify(searchForm));
                this.searchForm.pageSize = this.pagination.pageSize;
                if (nest==true){
                    this.searchForm.currentPage = this.pagination.currentPage;
                }else {
                    this.searchForm.currentPage = 1;
                }
                let preData = qs.stringify(this.searchForm, {indices: false});
                if (this.searchForm.bookName){
                    this.searchForm.bookName.trim();
                }
                if (this.searchForm.author){
                    this.searchForm.author.trim();
                }
                let data = {
                    params: {
                        'bookName': this.searchForm.bookName,
                        'author': this.searchForm.author,
                        'currentPage': this.pagination.currentPage,
                        'pageSize': this.pagination.pageSize
                    }
                }
                this.$axios.get(url, data).then(res => {
                    let myTaleData = res.data.result.items;
                    // let data = res.result.data.items;
                    // for(let key  in tableData){
                    //     if (key.indexOf('items')!=-1){
                    //         if (tableData[key]==null || tableData[key].length==0){
                    //             continue;
                    //         }
                    //         myTaleData =  data[key];
                    //         break;
                    //     }
                    // }
                    this.pagination.currentPage = res.data.result.currentPage;
                    this.pagination.pageSize = res.data.result.pageSize;
                    this.pagination.total = res.data.result.totalNum;
                    //to bus
                    eventBus.$emit(bus,myTaleData);
                }).catch((e) => {
                    console.log("e",e);
                });
            },

            handleSizeChange(val) {
                this.pagination.pageSize = val;
                this.$query(this.url,this.searchForm,this.bus,true);
            },

            handleCurrentChange(val) {
                this.pagination.currentPage = val;
                this.$query(this.url,this.searchForm,this.bus,true);
            },
        }
    }

</script>
<style scoped lang="sass">
</style>
