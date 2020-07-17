import * as types from "./mutation-types";
import getters from "./getters";
import actions from "./actions";

const roles = [
    {id: 1, name: "管理员", pages: [3, 4, 25, 26]},
    {id: 2, name: "普通用户", pages: [22, 23, 24,28,29,30]},
];

const pages = [
    {id: 0, name: "商家列表", url: "/shopList"},
    {id: 1, name: "店铺列表", url: "/storeBar"},
    {id: 2, name: "商品列表", url: "/goodsList"},
    {id: 3, name: "订单", url: "/order"}
];

const state = {
    $vm:{},
    isLoading: false,
    token: "",
    username: "",
    nav: [],
    roles: roles,
    role: {},
    title: "首页"
};

const stateProto = {};
Object.assign(stateProto, state);

const mutations = {
    [types.RESTORE_STATE](state, savedState) {
        Object.assign(state, savedState.common);
    },
    [types.NAV](state, status) {
        let t_page = [];
        for (let item of roles) {
            if (item.id === status) {
                t_page = item.pages;
                state.role = item;
            }
        }
        let list = [];
        for (let i of t_page) {
            list.push(pages[i]);
        }
        state.nav = list;
    },
    [types.UPDATE_LOADING](state, status) {
        state.isLoading = status;
    },
    [types.TITLE](state, status) {
        state.title = status;
    },
    [types.TOKEN](state, status) {
        state.token = status;
    },
    [types.USERNAME](state, status) {
        state.username = status;
    },
    [types.LANGUAGE](state, status) {
        state.languageCode = status;
    },
    [types.AREA](state, status) {
        state.areaCode = status;
    },
    [types.VUE](state, status) {
        state.$vm = status;
    },
    [types.LOGOUT](state) {
        Object.assign(state, stateProto);
    }
};

export default {
    state,
    getters,
    actions,
    mutations
};
