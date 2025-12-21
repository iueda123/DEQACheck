# DEQACheckAll セキュリティ設定

## 概要

DEQACheckAll Vaadinアプリケーションは、Spring Security を使用した認証システムを実装しています。
すべてのページへのアクセスには、ユーザー認証が必要です。

## 認証情報

デフォルトの認証情報は `application.properties` で設定されています：

```properties
# Authentication configuration
# Admin user (full access)
app.security.admin.username=admin
app.security.admin.password=deqacheck2025

# Guest user (limited access: Main and QASummaryPage only)
app.security.guest.username=guest
app.security.guest.password=guest2025
```

### ユーザーアカウント

| ユーザー | Account | Password | ロール | アクセス権限 |
|----------|---------|----------|--------|-------------|
| 管理者 | `admin` | `deqacheck2025` | USER, GUEST | 全ページ |
| ゲスト | `guest` | `guest2025` | GUEST | Main, QASummary のみ |

**注意:** 本番環境では必ずこれらの値を変更してください。

## 保護されるページ

`VaadinWebSecurity` を継承した設定により、以下のすべてのVaadinページが自動的に保護されます。
ロールに基づいてアクセス制御が行われます：

| ルート | ページ | アノテーション | Admin | Guest |
|--------|--------|---------------|:-----:|:-----:|
| `/` | MainView | `@RolesAllowed({"USER", "GUEST"})` | ○ | ○ |
| `/qa-summary` | QASummaryPage | `@RolesAllowed({"USER", "GUEST"})` | ○ | ○ |
| `/de-result-overview` | DEOverviewPage | `@RolesAllowed("USER")` | ○ | × |
| `/summary-view` | SummaryView | `@RolesAllowed("USER")` | ○ | × |
| `/summary-view-2` | SummaryView2 | `@RolesAllowed("USER")` | ○ | × |
| `/login` | LoginView | `@AnonymousAllowed` | - | - |

## 静的リソース

以下のパスは認証なしでアクセス可能です：

- `/images/**`
- `/icons/**`
- `/styles/**`

## 関連ファイル

### SecurityConfig.java

`src/main/java/iu/SpringBoot/Vaadin/security/SecurityConfig.java`

Spring Security の設定クラス。`VaadinWebSecurity` を継承し、以下を設定：

- インメモリ認証（`application.properties` から認証情報を読み込み）
- 管理者ユーザー（USER, GUEST ロール）とゲストユーザー（GUEST ロールのみ）
- ログインページの指定
- パスワードエンコーダー（BCrypt）

```java
@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    // Admin user credentials
    @Value("${app.security.admin.username:admin}")
    private String adminUsername;

    @Value("${app.security.admin.password:password}")
    private String adminPassword;

    // Guest user credentials
    @Value("${app.security.guest.username:guest}")
    private String guestUsername;

    @Value("${app.security.guest.password:guest}")
    private String guestPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 静的リソースへのアクセスを許可
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/images/**", "/icons/**", "/styles/**").permitAll()
        );

        super.configure(http);

        // ログインビューを設定
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Admin user - full access (USER role)
        UserDetails adminUser = User.builder()
            .username(adminUsername)
            .password(passwordEncoder().encode(adminPassword))
            .roles("USER", "GUEST")
            .build();

        // Guest user - limited access (GUEST role only)
        UserDetails guestUser = User.builder()
            .username(guestUsername)
            .password(passwordEncoder().encode(guestPassword))
            .roles("GUEST")
            .build();

        return new InMemoryUserDetailsManager(adminUser, guestUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### LoginView.java

`src/main/java/iu/SpringBoot/Vaadin/security/LoginView.java`

ログインページのVaadinビュー。`@AnonymousAllowed` アノテーションにより認証なしでアクセス可能。

## アクセス制御アノテーション

各ページのアクセス制御は以下のアノテーションで変更できます：

| アノテーション | 説明 |
|---------------|------|
| `@AnonymousAllowed` | 認証なしでアクセス可能 |
| `@PermitAll` | 認証済みユーザー全員がアクセス可能（デフォルト） |
| `@RolesAllowed("ADMIN")` | 特定のロールのみアクセス可能 |

### 使用例

```java
// 認証なしでアクセス可能なページ
@Route("public-page")
@AnonymousAllowed
public class PublicPage extends VerticalLayout { ... }

// 認証必須のページ（デフォルト）
@Route("protected-page")
public class ProtectedPage extends VerticalLayout { ... }

// ADMINロールのみアクセス可能
@Route("admin-page")
@RolesAllowed("ADMIN")
public class AdminPage extends VerticalLayout { ... }
```

## 認証フロー

1. ユーザーが保護されたページにアクセス
2. 未認証の場合、`/login` にリダイレクト
3. Account/Password を入力してログイン
4. 認証成功後、元のページにリダイレクト
5. 認証失敗時、エラーメッセージを表示

## 依存関係

`build.gradle` に以下の依存関係が必要です：

```gradle
implementation 'org.springframework.boot:spring-boot-starter-security'
```

## トラブルシューティング

### ログインできない場合

1. `application.properties` の認証情報を確認
2. パスワードの大文字/小文字を確認
3. アプリケーションを再起動

### ページにアクセスできない場合

1. ログイン状態を確認（セッションが切れていないか）
2. ブラウザのキャッシュをクリア
3. 別のブラウザで試す
