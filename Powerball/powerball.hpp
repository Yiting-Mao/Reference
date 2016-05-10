#ifndef POWERBALL_H_
#define POWERBALL_H_

#include <iostream>
#include <vector>

typedef struct prizes {
	long long jackpot;
	int five_white;
	int four_white_one_red;
	int four_white;
	int three_white_one_red;
	int three_white;
	int two_white_one_red;
	int two_white; 
	int one_white_one_red;
	int one_white;  
	int one_red;
} Prizes;

typedef struct number {
	int red;
	int first;
	int second;
	int third;
	int fourth;
	int fifth;
  bool operator == (const number &m) const {
    return ((m.red == red) && (m.first == first) && (m.second == second)
           && (m.third == third) && (m.fourth == fourth) && (m.fifth == fifth));
  }
  
  bool operator < (const number &a) const{
    if (a.red != red) {
      return a.red > red ? true : false;
    } else if (a.first != first) {
      return a.first > first ? true : false;
    } else if (a.second != second) {
      return a.second > second ? true : false;
    } else if (a.third != third) {
      return a.third > third ? true : false;
    } else if (a.fourth != fourth){
      return a.fourth > fourth ? true : false;
    } else {
      return a.fifth > fifth ? true : false;
    } 
  }
    
} Number;

typedef std::vector<Number> RecommendNumbers;

class Powerball;

class Strategy {
 public:
  Strategy() { recommend_numbers_ = NULL; }
  ~Strategy() { if (recommend_numbers_ != NULL) delete recommend_numbers_; }
	virtual void RecommendPowerball(const Powerball *ball) = 0;
  void PrintNumbers();
  RecommendNumbers *recommend_numbers_; 
};

class Powerball {
 public:
  
	Powerball(long long jackpot, int share_num, int one_cost=2);  
	void SetPrizes(long long, int = 1000000, int = 50000, int = 100, int = 100, 
                int = 7, int = 7, int = 0, int = 4, int = 0, int = 4);
	Prizes GetPrizes(void) const { return all_prizes_; }
	int GetOneCost(void) const { return one_cost_; }
	void GetRecommendNumbers(Strategy *s) const { s->RecommendPowerball(this); }
	
 private:
	Prizes all_prizes_;
	int one_cost_;
};

#endif