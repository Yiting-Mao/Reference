CC		= g++
TARGET	= powerball
OBJS	= main.o powerball.o randomrecommend.o

all: $(TARGET)

$(TARGET): $(OBJS)
	$(CC) -o $(TARGET) $(OBJS)

%.o: %.cpp
	$(CC) -c $<

clean: 
	rm powerball *.o
