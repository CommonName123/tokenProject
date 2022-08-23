import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import Vue from 'vue';
import User from "../types/User";


Vue.use(Vuex);

export const store = new Vuex.Store({
    modules: {
    },
    state: {
        user: null,
        section:'products'
    },
    mutations:{
        updateSection(state,section){
            state.section=section
        },
        updateUser(state: any, newUser: User) {
            state.user = newUser;
        },
    },
    actions:{
        changeSection(context,section){
            context.commit('updateSection',section);
        },
        changeUser(context: any, user: User) {
            context.commit('updateUser', user);
        },
        logOutUser(context: any) {
            context.commit('updateUser', null);
        },
    },
    getters: {
    },
    // plugins: [createPersistedState()]
});
export const CHANGE_USER = 'changeUser';

export const CHANGE_SECTION = 'changeSection';

export const LOG_OUT_USER = 'logOutUser';

