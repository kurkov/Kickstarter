package ua.goit.kickstarter.util;



public class Operation {
  Integer objectId;
  OperationType operationType;

  public Operation(Integer objectId, OperationType operationType) {
    this.objectId = objectId;
    this.operationType = operationType;
  }

  public Operation() {
  }

  public Integer getObjectId() {
    return objectId;
  }

  public void setObjectId(Integer objectId) {
    this.objectId = objectId;
  }

  public OperationType getOperationType() {
    return operationType;
  }

  public void setOperationType(OperationType operationType) {
    this.operationType = operationType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Operation operation = (Operation) o;

    if (objectId != operation.objectId) return false;
    if (operationType != operation.operationType) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (objectId ^ (objectId >>> 32));
    result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Operation{" +
        "objectId=" + objectId +
        ", operationType=" + operationType +
        '}';
  }
}
