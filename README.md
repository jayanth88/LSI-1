Sticky webserver(LSI-1)
=====

Naive sticky webserverthat ensure that the request from a client is always routed to the same backend server instance.
Implemented with a mix of cookies and server session.
Unlike a loadbalancer, this does not keep track of backend load.
