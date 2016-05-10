#ifndef RANDOMRECOMMEND_H_
#define RANDOMRECOMMEND_H_

#include <stdlib.h>
#include <time.h>
#include "powerball.hpp"

//This class only recommend using ramdon strategy, new strategies can always be created with new classes
class RandomRecommend : public Strategy {
public:
	RandomRecommend(int min, long long max);  
	void CalculateExpectation(Prizes prize); 
	void GetRandomNumbers(long long total);
  //returns one random set of number 
  Number GetNumber(int red);
  //replace duplicated sets of numbers
  void ReplaceDup();
	virtual void RecommendPowerball(const Powerball *ball);
	
private:
	double single_expect_return_;
	int min_money_;
	long long max_money_;
};

#endif