
class GeneralError extends Error {
  constructor(message) {
    super();
    this.message = message;
  }
}

class UnauthorizedError extends GeneralError {
  constructor(message) {
    super(message);
    this.statusCode = 401;
  }
}

class ForbiddenError extends GeneralError {
  constructor(message) {
    super(message);
    this.statusCode = 403;
  }
}

class NotFoundError extends GeneralError {
  constructor(message) {
    super(message);
    this.statusCode = 404;
  }
}

class ConflictError extends GeneralError {
  constructor(message) {
    super(message);
    this.statusCode = 409;
  }
}

class InternalServerError extends GeneralError {
  constructor(message) {
    super(message);
    this.statusCode = 500;
  }
}

module.exports = {
  GeneralError,
  UnauthorizedError,
  ForbiddenError,
  NotFoundError,
  ConflictError,
  InternalServerError
};