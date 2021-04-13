export class FullCouponDetails{
    
    public constructor(
        public id?:number,
        public name?:string,
        public amount?:number,
        public price?:number,        
        public description?:string,        
        public startDate?:Date,        
        public endDate?:Date,        
        public categoryType?:string,        
        public image?:string,        
        public companyName?:string
    ){}

}