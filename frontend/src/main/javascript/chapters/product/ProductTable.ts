import {Component} from "vue-property-decorator";
import Vue from "vue";
import productApi from "../../api/product/ProductApi";
import Product from "../../types/Product";
import WindowCard from "../../component/card/WindowCard.vue";
import AddProductForm from "./addProductFrom/AddProductForm.vue";
import {State} from "vuex-class";
import User from "../../types/User";

/**
 * Таблица продуктов
 * Created by CommonName123 on 22.08.2022
 */
@Component({components: {WindowCard, AddProductForm}})
export default class ProductTable extends Vue {
    public name: string = 'product-table';


    /**
     * Пользователь
     * @private
     */
    @State(state => state.user)
    private user!: User | null;

    /**
     * Список продуктов
     * @private
     */
    private products: Product[] = [];

    private selectedProduct: Product | null = null;

    /**
     * Признак открытого окна
     * @private
     */
    private dialogVisible: boolean = false;

    /**
     * Подтверждение удаления
     * @private
     */
    private confirmDialogVisible: boolean = false;

    /**
     * Инициализация
     * @private
     */
    private mounted() {
        this.loadTable();
    }

    /**
     * Загрузить данные в таблицу
     * @private
     */
    private loadTable() {
        productApi.getProducts().then((data: Product[]) => {
            this.products = data;
        });
    }

    /**
     * Открытие окна с добавлением продукта
     * @private
     */
    private openCard() {
        this.selectedProduct = new Product();
        this.dialogVisible = true;
    }

    /**
     * Событие при закрытии карточки
     * @private
     */
    private onCloseCard() {
        this.dialogVisible = false;
    }

    /**
     * Создание продукта
     * @private
     */
    private onSave() {
        const addForm: any = this.$refs.addForm;
        const formData: Product = addForm.form;
        productApi.updateProduct(formData).then(data => {

            // в теории вся эта работа с токеном в данном запросе излишняя
            // пользователь не сможет отправить этот запрос из интерфейса, ибо при отсутствии доступа на обновление
            // он не сможет загрузить картинку т.е. этот метод даже не вызовется,
            // однако, через какого-нибудь postman'а - запросто
            if (addForm.photoFile) {
                const formData = new FormData();
                formData.append("image", addForm.photoFile);

                const xhr = new XMLHttpRequest();
                xhr.open('POST', 'product/saveImage/' + data.id);
                xhr.setRequestHeader("Authorization","Bearer "+this.user?.token);
                xhr.onload = function (e: any) {
                    if (e.currentTarget.status !== 200) {
                        alert('Произошла ошибка при загрузке изображения');
                    }
                }
                xhr.send(formData);
            }


            this.loadTable();
            this.dialogVisible = false;
        });
    }


    /**
     * Событие выбора строки
     * @param row
     * @param column
     * @param event
     * @private
     */
    private selectRow(row: Product, column: any, event: any) {
        this.selectedProduct = row;
    }

    /**
     * Удалить выбранный продукт
     * @private
     */
    private deleteProduct() {
        if (this.selectedProduct && this.selectedProduct.id) {
            productApi.deleteProduct(this.selectedProduct.id).then(() => {
                const index = this.products.findIndex(x => x === this.selectedProduct);
                if (index >= 0) {
                    this.products.splice(index, 1);
                }
                this.confirmDialogVisible = false;
            });
        }

    }

    /**
     * Изменить выбранный продукт
     * @private
     */
    private editProduct() {
        this.dialogVisible = true;
    }

    /**
     * Создать картинку в таблице
     * @param photo
     * @private
     */
    private createSrc(photo:any){
        const blob = new Blob([photo],{type: 'image/png'});
        return URL.createObjectURL(blob);

    }
}