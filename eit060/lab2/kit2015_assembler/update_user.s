	.file	"update_user.c"
	.section	.rodata
.LC0:
	.string	"Usage: update_user username"
	.align 8
.LC1:
	.string	"pwdb_getpwnam return error: %s\n"
.LC2:
	.string	"Password:"
	.align 8
.LC3:
	.string	"New user, so you must enter data"
.LC4:
	.string	"Uid:"
.LC5:
	.string	"Gid:"
.LC6:
	.string	"Real name:"
.LC7:
	.string	"Home directory:"
.LC8:
	.string	"Shell:"
.LC9:
	.string	"Failed:"
.LC10:
	.string	"Age:"
	.align 8
.LC11:
	.string	"pwdb_update_user returned error %s\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB2:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$80, %rsp
	movl	%edi, -68(%rbp)
	movq	%rsi, -80(%rbp)
	cmpl	$1, -68(%rbp)
	jg	.L2
	movl	$.LC0, %edi
	call	puts
	movl	$0, %eax
	jmp	.L48
.L2:
	movq	-80(%rbp), %rax
	movq	8(%rax), %rax
	movq	%rax, -32(%rbp)
	movq	-32(%rbp), %rax
	movq	%rax, %rdi
	call	pwdb_getpwnam
	movq	%rax, -24(%rbp)
	cmpq	$0, -24(%rbp)
	je	.L4
	movl	$1, -52(%rbp)
	jmp	.L5
.L4:
	cmpq	$0, -24(%rbp)
	jne	.L6
	movl	pwdb_errno(%rip), %eax
	cmpl	$-1, %eax
	jne	.L6
	movl	$0, -52(%rbp)
	jmp	.L5
.L6:
	movl	pwdb_errno(%rip), %eax
	movl	%eax, %edi
	call	pwdb_err2str
	movq	%rax, %rsi
	movl	$.LC1, %edi
	movl	$0, %eax
	call	printf
	movl	$0, %eax
	jmp	.L48
.L5:
	movl	$56, %edi
	call	malloc
	movq	%rax, -16(%rbp)
	movq	$0, -48(%rbp)
	movq	$0, -40(%rbp)
	movq	-16(%rbp), %rax
	movq	-32(%rbp), %rdx
	movq	%rdx, (%rax)
	movq	$0, -8(%rbp)
.L11:
	movl	$.LC2, %edi
	movl	$0, %eax
	call	printf
	movq	stdin(%rip), %rdx
	leaq	-40(%rbp), %rcx
	leaq	-48(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	getline
	movq	%rax, -8(%rbp)
	movq	-48(%rbp), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	subq	$1, -8(%rbp)
	cmpl	$0, -52(%rbp)
	jne	.L7
	cmpq	$0, -8(%rbp)
	jne	.L7
	movl	$.LC3, %edi
	call	puts
.L7:
	cmpl	$0, -52(%rbp)
	je	.L8
	cmpq	$0, -8(%rbp)
	jne	.L8
	movq	-24(%rbp), %rax
	movq	8(%rax), %rdx
	movq	-16(%rbp), %rax
	movq	%rdx, 8(%rax)
	jmp	.L9
.L8:
	cmpq	$0, -8(%rbp)
	je	.L10
	movq	-8(%rbp), %rax
	addq	$1, %rax
	movq	%rax, %rdi
	call	malloc
	movq	%rax, %rdx
	movq	-16(%rbp), %rax
	movq	%rdx, 8(%rax)
	movq	-48(%rbp), %rcx
	movq	-16(%rbp), %rax
	movq	8(%rax), %rax
	movq	-8(%rbp), %rdx
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	strncpy
	movq	-16(%rbp), %rax
	movq	8(%rax), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	jmp	.L9
.L10:
	jmp	.L11
.L9:
	movq	$0, -8(%rbp)
.L16:
	movl	$.LC4, %edi
	movl	$0, %eax
	call	printf
	movq	stdin(%rip), %rdx
	leaq	-40(%rbp), %rcx
	leaq	-48(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	getline
	movq	%rax, -8(%rbp)
	movq	-48(%rbp), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	subq	$1, -8(%rbp)
	cmpl	$0, -52(%rbp)
	jne	.L12
	cmpq	$0, -8(%rbp)
	jne	.L12
	movl	$.LC3, %edi
	call	puts
.L12:
	cmpl	$0, -52(%rbp)
	je	.L13
	cmpq	$0, -8(%rbp)
	jne	.L13
	movq	-24(%rbp), %rax
	movl	16(%rax), %edx
	movq	-16(%rbp), %rax
	movl	%edx, 16(%rax)
	jmp	.L14
.L13:
	cmpq	$0, -8(%rbp)
	je	.L15
	movq	-48(%rbp), %rax
	movq	%rax, %rdi
	call	atoi
	movq	-16(%rbp), %rdx
	movl	%eax, 16(%rdx)
	jmp	.L14
.L15:
	jmp	.L16
.L14:
	movq	$0, -8(%rbp)
.L21:
	movl	$.LC5, %edi
	movl	$0, %eax
	call	printf
	movq	stdin(%rip), %rdx
	leaq	-40(%rbp), %rcx
	leaq	-48(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	getline
	movq	%rax, -8(%rbp)
	movq	-48(%rbp), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	subq	$1, -8(%rbp)
	cmpl	$0, -52(%rbp)
	jne	.L17
	cmpq	$0, -8(%rbp)
	jne	.L17
	movl	$.LC3, %edi
	call	puts
.L17:
	cmpl	$0, -52(%rbp)
	je	.L18
	cmpq	$0, -8(%rbp)
	jne	.L18
	movq	-24(%rbp), %rax
	movl	20(%rax), %edx
	movq	-16(%rbp), %rax
	movl	%edx, 20(%rax)
	jmp	.L19
.L18:
	cmpq	$0, -8(%rbp)
	je	.L20
	movq	-48(%rbp), %rax
	movq	%rax, %rdi
	call	atoi
	movq	-16(%rbp), %rdx
	movl	%eax, 20(%rdx)
	jmp	.L19
.L20:
	jmp	.L21
.L19:
	movq	$0, -8(%rbp)
.L26:
	movl	$.LC6, %edi
	movl	$0, %eax
	call	printf
	movq	stdin(%rip), %rdx
	leaq	-40(%rbp), %rcx
	leaq	-48(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	getline
	movq	%rax, -8(%rbp)
	movq	-48(%rbp), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	subq	$1, -8(%rbp)
	cmpl	$0, -52(%rbp)
	jne	.L22
	cmpq	$0, -8(%rbp)
	jne	.L22
	movl	$.LC3, %edi
	call	puts
.L22:
	cmpl	$0, -52(%rbp)
	je	.L23
	cmpq	$0, -8(%rbp)
	jne	.L23
	movq	-24(%rbp), %rax
	movq	24(%rax), %rdx
	movq	-16(%rbp), %rax
	movq	%rdx, 24(%rax)
	jmp	.L24
.L23:
	cmpq	$0, -8(%rbp)
	je	.L25
	movq	-8(%rbp), %rax
	addq	$1, %rax
	movq	%rax, %rdi
	call	malloc
	movq	%rax, %rdx
	movq	-16(%rbp), %rax
	movq	%rdx, 24(%rax)
	movq	-48(%rbp), %rcx
	movq	-16(%rbp), %rax
	movq	24(%rax), %rax
	movq	-8(%rbp), %rdx
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	strncpy
	movq	-16(%rbp), %rax
	movq	24(%rax), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	jmp	.L24
.L25:
	jmp	.L26
.L24:
	movq	$0, -8(%rbp)
.L31:
	movl	$.LC7, %edi
	movl	$0, %eax
	call	printf
	movq	stdin(%rip), %rdx
	leaq	-40(%rbp), %rcx
	leaq	-48(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	getline
	movq	%rax, -8(%rbp)
	movq	-48(%rbp), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	subq	$1, -8(%rbp)
	cmpl	$0, -52(%rbp)
	jne	.L27
	cmpq	$0, -8(%rbp)
	jne	.L27
	movl	$.LC3, %edi
	call	puts
.L27:
	cmpl	$0, -52(%rbp)
	je	.L28
	cmpq	$0, -8(%rbp)
	jne	.L28
	movq	-24(%rbp), %rax
	movq	32(%rax), %rdx
	movq	-16(%rbp), %rax
	movq	%rdx, 32(%rax)
	jmp	.L29
.L28:
	cmpq	$0, -8(%rbp)
	je	.L30
	movq	-8(%rbp), %rax
	addq	$1, %rax
	movq	%rax, %rdi
	call	malloc
	movq	%rax, %rdx
	movq	-16(%rbp), %rax
	movq	%rdx, 32(%rax)
	movq	-48(%rbp), %rcx
	movq	-16(%rbp), %rax
	movq	32(%rax), %rax
	movq	-8(%rbp), %rdx
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	strncpy
	movq	-16(%rbp), %rax
	movq	32(%rax), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	jmp	.L29
.L30:
	jmp	.L31
.L29:
	movq	$0, -8(%rbp)
.L36:
	movl	$.LC8, %edi
	movl	$0, %eax
	call	printf
	movq	stdin(%rip), %rdx
	leaq	-40(%rbp), %rcx
	leaq	-48(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	getline
	movq	%rax, -8(%rbp)
	movq	-48(%rbp), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	subq	$1, -8(%rbp)
	cmpl	$0, -52(%rbp)
	jne	.L32
	cmpq	$0, -8(%rbp)
	jne	.L32
	movl	$.LC3, %edi
	call	puts
.L32:
	cmpl	$0, -52(%rbp)
	je	.L33
	cmpq	$0, -8(%rbp)
	jne	.L33
	movq	-24(%rbp), %rax
	movq	40(%rax), %rdx
	movq	-16(%rbp), %rax
	movq	%rdx, 40(%rax)
	jmp	.L34
.L33:
	cmpq	$0, -8(%rbp)
	je	.L35
	movq	-8(%rbp), %rax
	addq	$1, %rax
	movq	%rax, %rdi
	call	malloc
	movq	%rax, %rdx
	movq	-16(%rbp), %rax
	movq	%rdx, 40(%rax)
	movq	-48(%rbp), %rcx
	movq	-16(%rbp), %rax
	movq	40(%rax), %rax
	movq	-8(%rbp), %rdx
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	strncpy
	movq	-16(%rbp), %rax
	movq	40(%rax), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	jmp	.L34
.L35:
	jmp	.L36
.L34:
	movq	$0, -8(%rbp)
.L41:
	movl	$.LC9, %edi
	movl	$0, %eax
	call	printf
	movq	stdin(%rip), %rdx
	leaq	-40(%rbp), %rcx
	leaq	-48(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	getline
	movq	%rax, -8(%rbp)
	movq	-48(%rbp), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	subq	$1, -8(%rbp)
	cmpl	$0, -52(%rbp)
	jne	.L37
	cmpq	$0, -8(%rbp)
	jne	.L37
	movl	$.LC3, %edi
	call	puts
.L37:
	cmpl	$0, -52(%rbp)
	je	.L38
	cmpq	$0, -8(%rbp)
	jne	.L38
	movq	-24(%rbp), %rax
	movl	48(%rax), %edx
	movq	-16(%rbp), %rax
	movl	%edx, 48(%rax)
	jmp	.L39
.L38:
	cmpq	$0, -8(%rbp)
	je	.L40
	movq	-48(%rbp), %rax
	movq	%rax, %rdi
	call	atoi
	movq	-16(%rbp), %rdx
	movl	%eax, 48(%rdx)
	jmp	.L39
.L40:
	jmp	.L41
.L39:
	movq	$0, -8(%rbp)
.L46:
	movl	$.LC10, %edi
	movl	$0, %eax
	call	printf
	movq	stdin(%rip), %rdx
	leaq	-40(%rbp), %rcx
	leaq	-48(%rbp), %rax
	movq	%rcx, %rsi
	movq	%rax, %rdi
	call	getline
	movq	%rax, -8(%rbp)
	movq	-48(%rbp), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	movb	$0, (%rax)
	subq	$1, -8(%rbp)
	cmpl	$0, -52(%rbp)
	jne	.L42
	cmpq	$0, -8(%rbp)
	jne	.L42
	movl	$.LC3, %edi
	call	puts
.L42:
	cmpl	$0, -52(%rbp)
	je	.L43
	cmpq	$0, -8(%rbp)
	jne	.L43
	movq	-24(%rbp), %rax
	movl	52(%rax), %edx
	movq	-16(%rbp), %rax
	movl	%edx, 52(%rax)
	jmp	.L44
.L43:
	cmpq	$0, -8(%rbp)
	je	.L45
	movq	-48(%rbp), %rax
	movq	%rax, %rdi
	call	atoi
	movq	-16(%rbp), %rdx
	movl	%eax, 52(%rdx)
	jmp	.L44
.L45:
	jmp	.L46
.L44:
	movq	-16(%rbp), %rax
	movq	%rax, %rdi
	call	pwdb_update_user
	testl	%eax, %eax
	je	.L47
	movl	pwdb_errno(%rip), %eax
	movl	%eax, %edi
	call	pwdb_err2str
	movq	%rax, %rsi
	movl	$.LC11, %edi
	movl	$0, %eax
	call	printf
.L47:
	movl	$0, %eax
.L48:
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE2:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 4.8.2-19ubuntu1) 4.8.2"
	.section	.note.GNU-stack,"",@progbits
