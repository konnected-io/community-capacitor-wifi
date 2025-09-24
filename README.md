## API

<docgen-index>

* [`getIP()`](#getip)
* [`getSSID()`](#getssid)
* [`connect(...)`](#connect)
* [`connectPrefix(...)`](#connectprefix)
* [`disconnect()`](#disconnect)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getIP()

```typescript
getIP() => Promise<WifiIPResult>
```

**Returns:** <code>Promise&lt;<a href="#wifiipresult">WifiIPResult</a>&gt;</code>

--------------------


### getSSID()

```typescript
getSSID() => Promise<WifiConnectionResult>
```

**Returns:** <code>Promise&lt;<a href="#wificonnectionresult">WifiConnectionResult</a>&gt;</code>

--------------------


### connect(...)

```typescript
connect(options: ConnectOptions) => Promise<WifiConnectionResult>
```

| Param         | Type                                                      |
| ------------- | --------------------------------------------------------- |
| **`options`** | <code><a href="#connectoptions">ConnectOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#wificonnectionresult">WifiConnectionResult</a>&gt;</code>

--------------------


### connectPrefix(...)

```typescript
connectPrefix(options: ConnectPrefixOptions) => Promise<WifiConnectionResult>
```

| Param         | Type                                                                  |
| ------------- | --------------------------------------------------------------------- |
| **`options`** | <code><a href="#connectprefixoptions">ConnectPrefixOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#wificonnectionresult">WifiConnectionResult</a>&gt;</code>

--------------------


### disconnect()

```typescript
disconnect() => Promise<void>
```

--------------------


### Interfaces


#### WifiIPResult

| Prop     | Type                        |
| -------- | --------------------------- |
| **`ip`** | <code>string \| null</code> |


#### WifiConnectionResult

| Prop       | Type                        |
| ---------- | --------------------------- |
| **`ssid`** | <code>string \| null</code> |


#### ConnectOptions

Options for connecting to an access point. `joinOnce` is only honoured on iOS,
while `isHiddenSsid` is only used on Android.

| Prop               | Type                 |
| ------------------ | -------------------- |
| **`ssid`**         | <code>string</code>  |
| **`password`**     | <code>string</code>  |
| **`joinOnce`**     | <code>boolean</code> |
| **`isHiddenSsid`** | <code>boolean</code> |


#### ConnectPrefixOptions

Options for prefix based connections (&gt;= Android 10 / iOS 13).

| Prop           | Type                 |
| -------------- | -------------------- |
| **`ssid`**     | <code>string</code>  |
| **`password`** | <code>string</code>  |
| **`joinOnce`** | <code>boolean</code> |

</docgen-api>
