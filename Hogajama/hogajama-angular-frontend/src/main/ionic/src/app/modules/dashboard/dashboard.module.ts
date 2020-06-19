import {NgModule} from '@angular/core';
import {IonicModule} from "@ionic/angular";
import {UnassignedDevicesComponent} from "./unassigned-devices/unassigned-devices.component";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import {MatButtonModule} from "@angular/material/button";
import {DashboardComponent} from "./dashboard.component";
import {DashboardRoutingModule} from "./dashboard-routing.module";

@NgModule({
    declarations: [
        UnassignedDevicesComponent,
        DashboardComponent
    ],
    exports: [],
    imports: [
        CommonModule,
        DashboardRoutingModule,
        SharedModule,
        IonicModule,
        MatButtonModule
    ]
})
export class DashboardModule {
}
