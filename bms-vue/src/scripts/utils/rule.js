export const validateMobile = v => {
  return /^[0-9]*$/.test(v);
};

export const validateEmail = v => {
  return /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(v);
};
export const doubleNum = v => {
  return /^[0-9]+(.[0-9]{1,2})?$/.test(v);
};
