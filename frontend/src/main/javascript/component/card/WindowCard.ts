import {Component, Emit, Prop} from "vue-property-decorator";
import Vue from "vue";

/**
 * Типовой компонент для окон
 * Created by CommonName123 on 23.08.2022
 */
@Component
export default class WindowCard extends Vue {
    public name: string = 'window-card';

    /**
     * Заголовок окна
     * @private
     */
    @Prop({default: 'Окно'})
    private title!: string;

    /**
     * Ширина мб как в % так и в px
     * @private
     */
    @Prop({default: '30%'})
    private width!: string;

    /**
     * Признак открыта ли карточка
     * @private
     */
    @Prop({ default: false })
    private dialogVisible!: boolean;

    /**
     * Нужна ли кнопка закрытия
     * @private
     */
    @Prop({default: false})
    private closeButton!: boolean;

    /**
     * Нужна ли кнопка сохранения
     * @private
     */
    @Prop({default: false})
    private saveButton!: boolean;


    /**
     * Закрытие окна
     * @private
     */
    @Emit()
    private onClose() {
        const windowCard :any = this.$refs.windowCard;
        windowCard.handleClose();
    }

    /**
     * Сохранение и закрытие
     * @private
     */
    @Emit()
    private onSave() {
        const windowCard :any = this.$refs.windowCard;
        windowCard.handleClose();
    }


    /**
     * Открыть карточку
     */
    public openCard() {
        this.dialogVisible = true;
    }

    /**
     * Закрыть карточку
     */
    public closeCard() {
        const windowCard :any = this.$refs.windowCard;
        windowCard.handleClose();
    }
}