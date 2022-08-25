import {Component, Prop, Watch} from "vue-property-decorator";
import Vue from "vue";
import Product from "../../../types/Product";
import categoryApi from "../../../api/category/CategoryApi";
import Category from "../../../types/Category";

/**
 * Форма для добалвения новых продуктов
 * Created by CommonName123 on 23.08.2022
 */
@Component
export default class AddProductForm extends Vue {
    public name: string = 'add-product-form';

    public form: Product = new Product();

    private categories: Category[] = [];

    public selectedCategoryId: string = "";

    private photo: string | null = null;

    public photoFile: any;

    /**
     * Объект полученый для инициализации
     * @private
     */
    @Prop({default: new Product()})
    private received!: Product;


    /**
     * Следить за изменениями во входном объекте
     * @param received
     * @private
     */
    @Watch('received', {immediate: true, deep: true})
    private changeReceivedData(received: Category) {
        if (this.received!=null){
            this.form = this.received;
            this.selectedCategoryId = this.received.category;
            if (this.form.photo !== null) {
                this.loadPhoto();
            }
        }
    }

    /**
     * Инициализация
     * @private
     */
    private mounted() {
        this.loadAutocomplete();

    }

    /**
     * Показать фото, если есть в сущности
     * @private
     */
    private loadPhoto() {
        this.photo = this.form.getPhotoToTable();
    }


    /**
     * Загрузить список категорий
     * @private
     */
    private loadAutocomplete() {
        categoryApi.getCategories().then(data => {
            this.categories = data;
        });
    }

    /**
     * При выборе категории привязываем её к новому продукту
     * @param selectedCategoryId
     * @private
     */
    private onSelectCategory(selectedCategoryId: string) {
        this.form.category = selectedCategoryId;
    }


    /**
     * Загрузка картинки
     * @param event
     * @private
     */
    private loadPicture(event: any) {
        const file = event.target.files[0];
        const fileReader = new FileReader();
        fileReader.addEventListener('load', this.readFile);
        fileReader.readAsArrayBuffer(file);

        this.photoFile = file;

    }

    private readFile(event: any) {
        const photo = event.target.result;
        this.photo = URL.createObjectURL(new Blob([photo], {type: 'image/png'}));
    }
}