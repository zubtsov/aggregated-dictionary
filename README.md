# aggregated-dictionary
todo: introduce Словоформа class instead of plain String
todo: replace nulls for enum fields with special values like "ОТСУТСТВУЕТ"
todo: различать (на данный момент null) отсутствие и неизвестность (например определенной словоформы)
todo: use dependency injection
todo: replace some classes with interfaces?
todo: сделать маппинг между словоформой и изменяемыми атрибутами many - to - many
todo: реализовать контроль непротиворечивости атрибутов (например, только глаголы прошедшего времени имеют род)
в том же классе, в конструкторе принимающем ImmutableAttributes или в отдельном.


todo: можно слово представить матрицей атрибуты\словоформы. или даже в виде многомерной матрицы...