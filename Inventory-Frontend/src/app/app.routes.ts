import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { InventoryComponent } from './inventory/inventory.component';
import { OrderComponent } from './order/order.component';
import { AdminComponent } from './admin/admin.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { AddInventoryComponent } from './add-inventory/add-inventory.component';
import { UpdateInventoryComponent } from './update-inventory/update-inventory.component';
import { SampleComponent } from './sample/sample.component';
import { OrderhistoryComponent } from './orderhistory/orderhistory.component';
import { OrderAcceptComponent } from './order-accept/order-accept.component';
import { RegisterComponent } from './register/register.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { roleAdminGuard } from './role-admin.guard';
import { roleUserGuard } from './role-user.guard';
import { authGuard } from './auth.guard';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';


export const routes: Routes = [
    {
        path:'',
        redirectTo:'login',
        pathMatch:'full'
    },
    {
        path:'login',
        component:LoginComponent
    },
    {
        path:'user',
        component:UserComponent
    },
    {
        path:'inventory',
        component:InventoryComponent , canActivate:[authGuard,roleUserGuard] 
    },
    {
        path:'order/:inventoryname',
        component:OrderComponent, canActivate:[roleUserGuard]
    },
    {
        path:'admin',
        component:AdminComponent, canActivate:[roleAdminGuard]
    },
    {
        path:'userprofile/:userid',
        component:UserprofileComponent, canActivate:[roleUserGuard]
    },
    {
        path:'editprofile',
        component:EditprofileComponent, canActivate:[roleUserGuard]
    },
    {
        path:'addInv',
        component:AddInventoryComponent, canActivate:[roleAdminGuard]
    },
    {
        path:'updateInv/:inventoryName',
        component:UpdateInventoryComponent , canActivate:[roleAdminGuard]
    },
    {
        path:'not-authorized',
        component:NotAuthorizedComponent
    },
    {
        path:'orderhistory',
        component:OrderhistoryComponent, canActivate:[roleAdminGuard]
    },
    {
        path:'orderhistory/orderAccept/:orderId',
        component:OrderAcceptComponent, canActivate:[roleAdminGuard]
    },
    {
        path:'register',
        component:RegisterComponent
    },{
        path:'resetpassword',
        component:ForgotpasswordComponent
    }

];
