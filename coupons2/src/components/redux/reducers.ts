import { Action } from "./Action";
import { ActionType } from "./ActionType";
import { AppState } from "./AppState";


export function reducer(currentState: AppState, action: Action) {
    switch (action.type) {
        case ActionType.Login:
            return (
                {
                    ...currentState,
                    isLoggedIn: true,
                    userDetails: action.payload
                }
            );
        case ActionType.Logout:
            return (
                {
                    ...currentState,
                    isLoggedIn: false
                }
            );
        case ActionType.getCoupons:
            return (
                {
                    ...currentState,
                    coupons: action.payload
                }
            );
        case ActionType.getCompanies:
            return (
                {
                    ...currentState,
                    companies: action.payload
                }
            );
        case ActionType.getUsers:
            return (
                {
                    ...currentState,
                    users: action.payload
                }
            );
        case ActionType.deleteCompany:
            let newStateCompanies = { ...currentState };
            let companyId = action.payload;
            for (let index = 0; index < newStateCompanies.companies.length; index++) {
                if (newStateCompanies.companies[index].id === companyId) {
                    newStateCompanies.companies.splice(index, 1);
                    break;
                }
            }
            return (
                newStateCompanies
            );
        case ActionType.deleteUser:
            let newStateUsers = { ...currentState };
            let userId = action.payload;
            for (let index = 0; index < newStateUsers.users.length; index++) {
                if (newStateUsers.users[index].id === userId) {
                    newStateUsers.users.splice(index, 1);
                    break;
                }
            }
            return (
                newStateUsers
            );
        case ActionType.getComponent3:
            return (
                {
                    ...currentState,
                    showComponent3: action.payload
                }
            );
        case ActionType.getUserDetails:
            return (
                {
                    ...currentState,
                    userDetails: action.payload
                }
            );
        default: return currentState;
    }
}