import Vue from 'vue';
import Router from 'vue-router';
import ProductTable from "./chapters/product/ProductTable.vue";
import CategoryTable from "./chapters/category/CategoryTable.vue";

Vue.use(Router);

export const routes = [
    {
        path: '/',
        redirect: '/products',
    },
    {
        path: '/products',
        name: 'products',
        component: ProductTable,
    },
    {
        path: '/categories',
        name: 'categories',
        component: CategoryTable,
    }
];

const router = new Router({ routes });

export default router;
