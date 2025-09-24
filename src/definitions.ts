export interface WifiIPResult {
  ip: string | null;
}

export interface WifiConnectionResult {
  ssid: string | null;
}

/**
 * Options for connecting to an access point. `joinOnce` is only honoured on iOS,
 * while `isHiddenSsid` is only used on Android.
 */
export interface ConnectOptions {
  ssid: string;
  password?: string;
  joinOnce?: boolean;
  isHiddenSsid?: boolean;
}

/** Options for prefix based connections (>= Android 10 / iOS 13). */
export interface ConnectPrefixOptions {
  ssid: string;
  password?: string;
  joinOnce?: boolean;
}

export interface WifiPlugin {
  getIP(): Promise<WifiIPResult>;
  getSSID(): Promise<WifiConnectionResult>;
  connect(options: ConnectOptions): Promise<WifiConnectionResult>;
  connectPrefix(options: ConnectPrefixOptions): Promise<WifiConnectionResult>;
  disconnect(): Promise<void>;
}
