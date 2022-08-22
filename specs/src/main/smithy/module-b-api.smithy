namespace smithy4s.hello

use smithy4s.api#simpleRestJson

@simpleRestJson
service ModuleBService {
  version: "1.0.0",
  operations: [Hi]
}

@http(method: "GET", uri: "/", code: 200)
@readonly
operation Hi {
  output: Greeting
}

structure Greeting {
  @required
  message: String
}