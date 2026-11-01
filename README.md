# k8s-java17

A small Java 17 + Maven sample project (payment fees and settlement) used as a build target for
[k8s-platform](https://github.com/tanmaypatil/k8s-platform).

The build agent there reads `maven.compiler.release` = `17` from `pom.xml` and runs the build in the
`java17-maven` image. This code will **not** compile on Java 8, which proves the right image was picked.

Java 17 features used:

| Feature | Where |
|---|---|
| Sealed interfaces (`sealed ... permits`) | `Payment` |
| Records, compact constructors | `Money`, `CardPayment`, `UpiPayment`, `WalletPayment`, `SettlementReport.Line` |
| Pattern matching for `instanceof` | `FeeCalculator.fee` |
| Switch expressions, `yield` | `FeeCalculator` |
| Text blocks, `String.formatted` | `SettlementReport.render` |
| `var`, `Stream.toList()` | throughout |

```bash
mvn -B package
java -jar target/payments-java17-1.0.0.jar
```
