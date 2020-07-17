import Moment from "moment";
function isRealNum(val) {
  // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
  if (val === "" || val == null) {
    return false;
  }
  if (!isNaN(+val)) {
    return true;
  } else {
    return false;
  }
}
function formatNumbr(num, data) {
  if (data) {
    return +(+num / data).toFixed(2) || 0;
  } else {
    return +(+num).toFixed(2) || 0;
  }
}
export default {
  isRealNum,
  formatNumbr,
  formatto100Fix(row, column) {
    const num = eval(`row.${column.property}`);
    if (row.id === -1) {
      return isRealNum(num) ? formatNumbr(num, 100) : "";
    } else {
      return isRealNum(num) ? formatNumbr(num, 100) : "--";
    }
  },
  formattoFix(row, column) {
    const num = eval(`row.${column.property}`);
    if (row.id === -1) {
      return isRealNum(num) ? num : "";
    }
    return isRealNum(num) ? num : "--";
  },
  formatistrue(row, column) {
    if (row.id !== -1) {
      return eval(`row.${column.property}`) || "--";
    }
    return eval(`row.${column.property}`);
  },
  formatPercen(row, column) {
    if (row.id !== -1) {
      const num = eval(`row.${column.property}`);
      if (isRealNum(num) && num >= 0) {
        return `${num}%`;
      }
      return "--";
    }
    return eval(`row.${column.property}`);
  },
  formatDate(row, column) {
    if (row.id !== -1) {
      const date = eval(`row.${column.property}`);
      if (date) {
        return Moment(date).format("YY.MM.DD");
      }
      return "--";
    }
    return eval(`row.${column.property}`);
  },
  formatYYYYDate(row, column) {
    if (row.id !== -1) {
      const date = eval(`row.${column.property}`);
      if (date) {
        return Moment(date).format("YYYY.MM.DD");
      }
      return "--";
    }
    return eval(`row.${column.property}`);
  }
};
