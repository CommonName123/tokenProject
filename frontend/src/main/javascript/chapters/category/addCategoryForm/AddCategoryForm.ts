import {Component, Prop, Watch} from "vue-property-decorator";
import Vue from "vue";
import Category from "../../../types/Category";

/**
 * Форма для добалвения/изменения категорий
 * Created by CommonName123 on 24.08.2022
 */
@Component
export default class AddCategoryForm extends Vue {
    public name: string = 'add-category-form';

    /**
     * Редактируемый объект
     */
    public form: Category = new Category();

    /**
     * Объект полученый для инициализации
     * @private
     */
    @Prop({default: new Category()})
    private received!: Category;


    /**
     * Следить за изменениями во входном объекте
     * @param received
     * @private
     */
    @Watch('received', {immediate:true,deep: true})
    private changeReceivedData(received: Category) {
        if (this.received!=null){
            this.form = this.received;
        }
    }
}