<template >
</template>
<script>
    import Vue from "vue";
    import { eventBus } from "scripts/utils/BUS";
    import qs from 'qs';
    // eslint-disable-next-line no-irregular-whitespace
    export default {
        // eslint-disable-next-line no-irregular-whitespace
        data () {
            return {
                listLoading:false
            }
        },
        mounted(){

        },
        // eslint-disable-next-line no-irregular-whitespace
        methods: {
            $DeleteById(url,id,bus,idType){
                let aa = "删除成功"
                let bb = "Failed to delete, please contact the administrator"
                this.$axios.get(url+'?bid='+id).then(res => {
                    this.$alert(aa, '', {
                        confirmButtonText: "OK",
                        callback: () => {
                            //to bus
                            eventBus.$emit(bus);
                        }
                    });
                }).catch((e) => {
                        this.$alert(bb, '', {
                        confirmButtonText: "OK",
                    });
                    return false;
                });
            },

            //All no use page queries use this method
            $noPageQuery(url,bus){
                this.$axios.post(url,null).then(res => {
                    let tableData = res.data.data;
                    let data = res.data.data;
                    for(let key  in data){
                        if (key.indexOf('List')!=-1){
                            tableData =  tableData[key]
                            break;
                        }
                    }
                    //to bus
                    eventBus.$emit(bus,tableData);
                }).catch((e) => {
                    this.$alert('查询失败，请联系管理员', '', {
                        confirmButtonText: '确定',
                    });
                });
            },

            $ConfirmData(url,form,text,bus){
                Vue.delete(form, 'createTime' );
                Vue.delete(form, 'createTIme' );
                Vue.delete(form, 'updateTime' );
                Vue.delete(form, 'updateTIme' );
                Vue.delete(form, 'createBy' );
                Vue.delete(form, 'updateBy' );
                this.$axios.get(url, form).then(res => {
                    this.$alert(text, '', {
                        confirmButtonText: "OK",
                        callback: () => {
                            eventBus.$emit(bus,null);
                        }
                    });
                });
            },
        }
    }
</script>
