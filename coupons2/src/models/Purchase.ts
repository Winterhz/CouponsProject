export class Purchase {
    public constructor(
        public id?: number,
        public amount?: number,
        public timeOfPurchase?: Date,
        public couponName?: string,
        public userId?: number
    ) { }

}