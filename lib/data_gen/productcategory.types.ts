import {EntityType, KV} from '../base/baseservice.types';

export type GetAllProductCategoryParams = KV & {};


export type ProductCategoryList = Array<ProductCategory>;

export interface ProductCategory extends EntityType {
}
