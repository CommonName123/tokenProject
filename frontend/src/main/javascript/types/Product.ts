/**
 * Класс описывающий продукт
 * Created by CommonName123 22.08.2022
 */
export default class Product {
    readonly id!:number;            // Идентификатор
    readonly name!:string;          // Имя
    private description!:string;    // Заметка
    private price!:number;          // Цена
    private photo!:number;          // Фотография
    public category!:any;       // Категория
    private categoryName!:string;       // Категория
    private date!:number;           // Дата добавления в каталог
    private status!:number;         // Статус
}