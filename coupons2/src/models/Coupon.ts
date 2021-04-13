export class Coupon {
    map(arg0: (coupon: any) => JSX.Element): import("react").ReactNode {
        throw new Error('Method not implemented.');
    }
    public constructor(
        public id: number,
        public name: string,
        public amount: number,
        public price: number,
        public categoryType: string,
        public companyName: string,
        public image: string
    ) { }

}