import { Company } from "../../models/Company";
import { Coupon } from "../../models/Coupon";
import { UserDetails } from "../../models/UserDetails";

export class AppState {
     isLoggedIn: boolean = false;
     userDetails: UserDetails = null;
     coupons: Coupon[] = [];
     companies: Company[] = [];
     users: UserDetails[] = [];
     showComponent3: boolean = false;
}