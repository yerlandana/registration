# registration

a = input()

b = list(map(int, a.split()))
checklist = list(map(int, a.split()))

for j in range(len(checklist) - 1):
   minimal_index = j
   minimal_value = checklist[j]
   for i in range(j + 1, len(checklist)):
       if checklist[i] < minimal_value:
           minimal_index = i
           minimal_value = checklist[i]
   checklist[minimal_index] = checklist[j]
   checklist[j] = minimal_value

if checklist == b:
    print('YES')
    print(b)
else:
    print('NO')
    print(checklist)
        
