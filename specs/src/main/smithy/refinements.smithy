namespace smithy4s.hello

use smithy4s.meta#refinement

@trait(selector: "string")
@refinement(
  targetType: "smithy4s.hello.refined.Email"
)
structure emailFormat {}

@emailFormat
string Email


