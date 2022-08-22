import {Component, Watch} from "vue-property-decorator";
import Vue from "vue";
import {State, Action} from 'vuex-class';
import MyNavigation from "../navigation/MyNavigation.vue";
import {CHANGE_SECTION} from "../stores/store";
import Login from "../chapters/login/LoginScreen.vue";

/**
 * Главное окно приложения
 * Created by CommonName123 25.05.2022
 */
@Component({components: {MyNavigation,Login}})
export default class NewMenu extends Vue {
    public name: string = 'new-menu';

    /**
     * Выбранный раздел
     * @private
     */
    @State(state => state.section)
    private section!: string;

    /**
     * Выбор нового раздела
     * @private
     */
    @Action(CHANGE_SECTION)
    private updateSection!: (newSection: string) => void;

    /**
     * Инициализация
     * @private
     */
    private mounted(){
        this.updateLink();
    }

    /**
     * При смене роутера/ссылки
     */
    @Watch('$route', {immediate: true})
    public onRouterChange() {
        this.$nextTick(()=>this.updateLink());
    }

    /**
     * Обновить раздел
     * @private
     */
    private updateLink() {
        const routePath = this.$route ? this.$route.path : '';
        if (routePath) {
            this.updateSection(routePath.slice(1, routePath.length));
        } else {
            this.updateSection('login');
        }
    }
}

