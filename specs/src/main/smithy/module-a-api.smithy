namespace smithy4s.hello

use smithy4s.api#simpleRestJson

@simpleRestJson
service ModuleAService {
  version: "1.0.0",
  operations: [Hello]
}

@http(method: "POST", uri: "/{name}", code: 200)
operation Hello {
  input: Person,
  output: Greeting
}

structure Person {
  @httpLabel
  @required
  name: String,

  @required
  @httpQuery("email")
  email: Email,

  @httpQuery("town")
  town: String
}

structure Greeting {
  @required
  message: String
}