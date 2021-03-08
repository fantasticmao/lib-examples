/**
 * 练习 5-8
 *
 * 函数 day_of_year 和 month_day 中没有进行错误检查，请解决改问题。
 */

#include <math.h>
#include <stdio.h>

/* day_of_year 函数：将某年某月的日期表示形式转换为某年中第几天的表现形式 */
int day_of_year(int year, int month, int day);

/* month_day 函数：将某年中第几天的日期表示形式转换为某月某日的表示形式 */
void month_day(int year, int yearday, int *pmonth, int *pday);

static int is_leap_year(int year);

static char daytab[2][13] = {
    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
};

int main(int argc, char *argv[]) {
  const int year = 2021, month = 3, day = 8;
  const int days = day_of_year(year, month, day);
  printf("%d-%d is the %dth day in %d year\n", month, day, days, year);

  int m, d;
  month_day(year, days, &m, &d);
  printf("%dth day in %d year is %d-%d\n", days, year, m, d);
  return 0;
}

int day_of_year(int year, int month, int day) {
  month = (month < 1) ? 1 : month;
  month = (month > 12) ? 12 : month;

  int leap = is_leap_year(year);
  day = (day < 0) ? 0 : day;
  day = (day > daytab[leap][month]) ? daytab[leap][month] : day;

  for (int i = 1; i < month; i++) {
    day = day + daytab[leap][i];
  }
  return day;
}

void month_day(int year, int yearday, int *pmonth, int *pday) {
  int leap = is_leap_year(year);
  int alldays = leap ? 366 : 365;
  yearday = (yearday < 0) ? 0 : yearday;
  yearday = (yearday > alldays) ? alldays : yearday;

  int i;
  for (i = 1; yearday > daytab[leap][i]; i++) {
    yearday = yearday - daytab[leap][i];
  }
  *pmonth = i;
  *pday = yearday;
}

static int is_leap_year(int year) {
  return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
}