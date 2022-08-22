import {plainToClass} from 'class-transformer';
import {ClassConstructor} from "class-transformer/types/interfaces";


/**
 * Трансормация в объект
 * @param clazz
 * @param data
 */
export function dataToClass<T, V>(clazz: ClassConstructor<T>,data:V[]):T{
    const result = plainToClass(clazz,data);
    if (Array.isArray(result)){
        return result[0];
    }
    return result;
}

/**
 * Трансормация в список
 * @param clazz
 * @param data
 */
export function dataToArrayClass<T, V>(clazz: ClassConstructor<T>,data:V[]):T[]{
    const result = plainToClass(clazz,data);
    if (Array.isArray(result)){
        return result;
    }
    return [result];
}
