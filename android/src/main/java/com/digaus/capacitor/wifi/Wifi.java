package com.digaus.capacitor.wifi;

import android.Manifest;
import android.os.Build;

import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;


@CapacitorPlugin(
    name = "Wifi",
    permissions = {
        @Permission(
            alias = "fineLocation",
            strings = { Manifest.permission.ACCESS_FINE_LOCATION }
        ),
        @Permission(
            alias = "nearbyDevices",
            strings = { Manifest.permission.NEARBY_WIFI_DEVICES }
        ),
    }
)
public class Wifi extends Plugin {

    private static final int API_VERSION = Build.VERSION.SDK_INT;

    private static final String PERMISSION_FINE_LOCATION = "fineLocation";
    private static final String PERMISSION_NEARBY_DEVICES = "nearbyDevices";

    WifiService wifiService;

    @Override
    public void load() {
      super.load();
      this.wifiService = new WifiService();
      this.wifiService.load(this.bridge);
    }
    
    @PluginMethod()
    public void getIP(PluginCall call) {
        if (needsPermissions()) {
            requestRequiredPermissions(call);
            return;
        }
        this.wifiService.getIP(call);
    }

    @PluginMethod()
    public void getSSID(PluginCall call) {
        if (needsPermissions()) {
            requestRequiredPermissions(call);
            return;
        }
        this.wifiService.getSSID(call);
    }

    @PluginMethod()
    public void connect(PluginCall call) {
        if (!isValidSsid(call)) {
            call.reject("Must provide an ssid");
            return;
        }
        if (needsPermissions()) {
            requestRequiredPermissions(call);
            return;
        }
        this.wifiService.connect(call);

    }

    @PluginMethod()
    public void connectPrefix(PluginCall call) {
        if (!isValidSsid(call)) {
            call.reject("Must provide an ssid");
            return;
        }
        if (needsPermissions()) {
            requestRequiredPermissions(call);
            return;
        }
        this.wifiService.connectPrefix(call);

    }

    @PluginMethod()
    public void disconnect(PluginCall call) {
        this.wifiService.disconnect(call);
    }

    @PermissionCallback
    private void permissionsCallback(PluginCall call) {
        if (!needsPermissions()) {
            if (call.getMethodName().equals("getSSID")) {
                this.wifiService.getSSID(call);
            } else if (call.getMethodName().equals("getIP")) {
                this.wifiService.getIP(call);
            } else if (call.getMethodName().equals("connect")) {
                this.wifiService.connect(call);
            } else if (call.getMethodName().equals("connectPrefix")) {
                this.wifiService.connectPrefix(call);
            }
            return;
        }
        call.reject("User denied permission");
    }

    private boolean needsPermissions() {
        if (API_VERSION >= Build.VERSION_CODES.TIRAMISU && getPermissionState(PERMISSION_NEARBY_DEVICES) != PermissionState.GRANTED) {
            return true;
        }
        return API_VERSION >= Build.VERSION_CODES.M && getPermissionState(PERMISSION_FINE_LOCATION) != PermissionState.GRANTED;
    }

    private void requestRequiredPermissions(PluginCall call) {
        if (API_VERSION >= Build.VERSION_CODES.TIRAMISU && getPermissionState(PERMISSION_NEARBY_DEVICES) != PermissionState.GRANTED) {
            requestPermissionForAliases(new String[] { PERMISSION_FINE_LOCATION, PERMISSION_NEARBY_DEVICES }, call, "permissionsCallback");
        } else if (API_VERSION >= Build.VERSION_CODES.M && getPermissionState(PERMISSION_FINE_LOCATION) != PermissionState.GRANTED) {
            requestPermissionForAlias(PERMISSION_FINE_LOCATION, call, "permissionsCallback");
        }
    }

    private boolean isValidSsid(PluginCall call) {
        if (!call.hasOption("ssid")) {
            return false;
        }
        String ssid = call.getString("ssid");
        return ssid != null && !ssid.trim().isEmpty();
    }
}
