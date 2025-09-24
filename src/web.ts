import { WebPlugin } from '@capacitor/core';

import type {
  ConnectOptions,
  ConnectPrefixOptions,
  WifiConnectionResult,
  WifiIPResult,
  WifiPlugin,
} from './definitions';

export class WifiWeb extends WebPlugin implements WifiPlugin {
  async getIP(): Promise<WifiIPResult> {
    return { ip: null };
  }

  async getSSID(): Promise<WifiConnectionResult> {
    return { ssid: null };
  }

  async connect(options: ConnectOptions): Promise<WifiConnectionResult> {
    // Browser platform cannot manage WiFi, log so callers know it ran.
    console.log('Wifi.connect is not supported on web.', options);
    return { ssid: null };
  }

  async connectPrefix(options: ConnectPrefixOptions): Promise<WifiConnectionResult> {
    console.log('Wifi.connectPrefix is not supported on web.', options);
    return { ssid: null };
  }

  async disconnect(): Promise<void> {
    // Nothing to do on web.
  }
}
