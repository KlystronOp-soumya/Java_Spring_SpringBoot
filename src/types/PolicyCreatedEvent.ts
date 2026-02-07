import { Customer } from "./Customer";
import { Policy } from "./Policy";

export interface PolicyCreatedEvent {
  eventId: string;
  eventType: string;
  occurredAt: string;

  policy: Policy;
  customer: Customer;
}