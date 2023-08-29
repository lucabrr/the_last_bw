package com.epicenergy.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epicenergy.entity.Comune;
import com.epicenergy.entity.ERole;
import com.epicenergy.entity.Indirizzo;
import com.epicenergy.entity.Role;
import com.epicenergy.entity.User;
import com.epicenergy.enums.RagioneSociale;
import com.epicenergy.exception.MyAPIException;
import com.epicenergy.payload.LoginDto;
import com.epicenergy.payload.RegisterDto;
import com.epicenergy.repository.IComuneDAO;
import com.epicenergy.repository.IProvinciaDao;
import com.epicenergy.repository.IndirizzoRepository;
import com.epicenergy.repository.RoleRepository;
import com.epicenergy.repository.UserRepository;
import com.epicenergy.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    IProvinciaDao prov_dao;
    @Autowired
    IComuneDAO com_dao;
    @Autowired
    IndirizzoRepository ind_dao;

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public User register(RegisterDto registerDto) {

        // add check for username exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // add check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setLastname(registerDto.getLastname());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setDataInserimento(LocalDateTime.now());
        user.setDataUltimoContatto(null);
        user.setTelefono(registerDto.getTelefono());
        user.setRagioneSociale(RagioneSociale.valueOf(registerDto.getRagioneSociale().toString()));

        Indirizzo ind = new Indirizzo();
        Comune com = com_dao.findByNome(registerDto.getIndirizzo().getComune().getNome());
        ind.setVia(registerDto.getIndirizzo().getVia());
        ind.setCivico(registerDto.getIndirizzo().getCivico());
        ind.setLocalita(registerDto.getIndirizzo().getLocalita());
        ind.setCap(registerDto.getIndirizzo().getCap());
        ind.setComune(com);

        if (ind.getComune() != null) {
            ind_dao.save(ind);
            user.setIndirizzo(ind);
        } else {
            // per testing
            System.out.println("Input Comune nome " + registerDto.getIndirizzo().getComune().getNome());
            System.out
                    .println("Input Provincia sigla "
                            + registerDto.getIndirizzo().getComune().getProvincia().getSigla());
            System.out.println("Query Comune nome " + com.getNome());
        }

        // optional fields
        user.setPartitaIva(registerDto.getPartitaIva());
        user.setFatturatoAnnuale(registerDto.getFatturatoAnnuale());
        user.setPec(registerDto.getPec());
        user.setEmailContatto(registerDto.getEmailContatto());
        user.setNomeContatto(registerDto.getNomeContatto());
        user.setCognomeContatto(registerDto.getCognomeContatto());
        user.setTelefonoContatto(registerDto.getTelefonoContatto());

        // user role default
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        roles.add(userRole);

        user.setRoles(roles);
        // System.out.println(user);
        userRepository.save(user);

        return user;
    }

    public ERole getRole(String role) {
        if (role.equals("ADMIN"))
            return ERole.ROLE_ADMIN;
        else if (role.equals("MODERATOR"))
            return ERole.ROLE_MODERATOR;
        else
            return ERole.ROLE_USER;
    }

}
