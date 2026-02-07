import { InvocationContext } from "@azure/functions";

export type TypedServiceBusHandler<T> = (
  message: T,
  context: InvocationContext
) => Promise<void>;