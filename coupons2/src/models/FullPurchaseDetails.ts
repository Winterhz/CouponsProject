export class FullPurchaseDetails{
    
    public constructor(
        public id?:number,
        public timeOfPurchase?:Date,
        public couponName?:string,
        public price?:number,
        public description?:string,
        public startDate?:Date,
        public endDate?:Date,
        public companyName?:string
    ){}

}